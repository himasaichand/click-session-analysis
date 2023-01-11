package com.hima

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.udf


class TransformLoad {


  val sparksession = SparkSession.builder().config("spark.master", "local").config("spark.sql.crossJoin.enabled", "true").getOrCreate()
  sparksession.sparkContext.setLogLevel("ERROR")

  import sparksession.implicits._

  def clickSessList(tmo: Double) = udf { (uid: String, clickList: Seq[String], tsList: Seq[Double]) =>
    def sid(n: Double) = s"$uid-$n"

    val sessList = tsList.foldLeft((List[String](), 0d, 0d)) { case ((ls, j, k), i) =>
      if (i == 0 || j + i >= tmo) {
       // println("j value in first loop -->" + j + "i value =>" + i)
        (sid(k + 1) :: ls, 0d, k + 1)
      }
      else {
        //println("j value in second loop -->" + j + "i value =>" + i)
        (sid(k) :: ls, j + i, k)
      }
    }._1.reverse

    clickList zip sessList
  }

  import sparksession.implicits._
  import org.apache.spark.sql.functions._


  val clickStreamDf = sparksession.read.options(Map("inferSchema" -> "true", "delimiter" -> ",", "header" -> "true")).csv("src/main/resources/Session_activity.csv")

  clickStreamDf.show(false)
  val windowPeriod = Window.partitionBy($"userid").orderBy($"timestamp")
  val lagval = clickStreamDf.withColumn("lag_value", lag($"timestamp", 1).over(windowPeriod))
  val timeDiff = lagval.withColumn("time_diff", (unix_timestamp($"timestamp") - unix_timestamp($"lag_value")) / 60).withColumn("time_diff", when(row_number.over(windowPeriod) === 1 || $"time_diff" >= 30, 0).otherwise($"time_diff"))
  timeDiff.show(false)
  //val sessionCal=timeDiff.withColumn("new_session_cal",when($"time_diff">30,1).otherwise(0))
  //val sessionName=sessionCal.withColumn("session_name",concat($"userid",lit("--session"),sum($"new_session_cal").over(windowPeriod))).withColumn("session_test",sum($"time_diff").over(windowPeriod))
  //sessionName.show(false)

  val finalDf = timeDiff.
    groupBy("userid").agg(
    collect_list($"timestamp").as("click_list"), collect_list($"time_diff").as("ts_list")
  ).
    withColumn("click_sess_id",
      explode(clickSessList(120.0d)($"userid", $"click_list", $"ts_list"))
    ).
    select($"userid", $"click_sess_id._1".as("click_time"), $"click_sess_id._2".as("sess_id"))

  finalDf.show(false)

  // finalDf.save
  //clickstreamDF.write.mode(SaveMode.Append).parquet("out.parquet")
  finalDf.write.mode("overwrite").parquet("src/main/resources/output.parquet")

  finalDf.createOrReplaceTempView("session_info")

  /** ********************************************
   * •	Get Number of sessions generated in a day.
   * •	Total time spent by a user in a day
   * •	Total time spent by a user over a month.
   * ********************************************** */


  sparksession.sql("select dayofmonth( click_time) AS day ,count(sess_id) as sessions from session_info group by dayofmonth( click_time), year( click_time),month( click_time) ").show(false)

  sparksession.sql("select userid,day,sum(Total_time_per_usersession) from  (select userid,dayofmonth( click_time) as day, (max(unix_timestamp(click_time))-min(unix_timestamp(click_time)))/60      AS Total_time_per_usersession  from session_info group by userid,sess_id,dayofmonth( click_time), year( click_time),month( click_time)) group by userid,day " +
    //"group by userid,dayofmonth( click_time), year( click_time),month( click_time)" +
    " ").show(false)

  //sparksession.sql("select userid,dayofmonth( click_time) AS day,  sum(1)  AS Total_time_min  from session_info group by userid,dayofmonth( click_time), year( click_time),month( click_time) ").show(false)

  sparksession.sql("select userid,year,month,sum(Total_time_per_usersession) from  (select userid,year( click_time) as year,month( click_time) as month, (max(unix_timestamp(click_time))-min(unix_timestamp(click_time)))/60      AS Total_time_per_usersession  from session_info group by userid,sess_id,year( click_time),month( click_time)) group by userid,year,month ").show(false)

}
