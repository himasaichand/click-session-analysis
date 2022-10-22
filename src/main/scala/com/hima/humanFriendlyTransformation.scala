package com.hima

import scala.util.Try
class humanFriendlyTransformation {

  var num_to_words= Map(0->  "twelve", 1-> "one", 2-> "two", 3-> "three", 4-> "four", 5-> "five", 6-> "six", 7-> "seven", 8-> "eight",
    9-> "nine", 10-> "ten", 11-> "eleven", 12-> "twelve", 13-> "thirteen", 14-> "fourteen", 15-> "fifteen",
    16-> "sixteen", 17-> "seventeen", 18-> "eighteen", 19-> "nineteen", 20-> "twenty", 30-> "half")


  val PAST = "past"
  val TO = "to"
  val CLOCK = "o'clock"
  val SPACE = " "

  var outputime=""


  def human_transform (time : String ): String={

    CheckTimeFormat(time)

    var hour =time.split(":")(0).toInt
    var minute=time.split(":")(1).toInt

    hour%=12


    if (minute==0)
    {
      outputime=time+SPACE+hmToWord(hour).capitalize+SPACE+CLOCK
    }
    else if (minute>30)
    {
      hour+=1
      minute =60-minute
      outputime=time+SPACE+hmToWord(minute).capitalize+SPACE+TO+SPACE+hmToWord(hour)

    }
    else
    {
      outputime=time+SPACE+hmToWord(minute).capitalize+SPACE+PAST+SPACE+hmToWord(hour)
    }

    outputime
  }

  def hmToWord(num : Int): String= {

    if (num<20 || num==30)
    {
      num_to_words(num)
    }
    else
    {
      num_to_words(20) + SPACE + num_to_words(num - 20)
    }

  }

  def CheckTimeFormat(intime:String){

    val hourNum=intime.split(":")(0)
    val minNum=intime.split(":")(1)


    if (intime.length <4 || intime.length>5 || !intime.contains(":") || intime.reverse.charAt(2)!=':'
    )
      throw new IllegalArgumentException("Time format is not valid. Usage format -> HH:MM or H:MM ")
    else if(Try(hourNum.toInt).isFailure || hourNum.toInt>23 || hourNum.toInt<0)
      {
        throw new IllegalArgumentException("Invalid hour. Usage --> value should be in 0 to 23")
      }
    else if(Try(minNum.toInt).isFailure || minNum.toInt>59 || minNum.toInt<0)
      {
        throw new IllegalArgumentException("Invalid minutes. Usage --> value should be in 0 to 59")
      }
  }





}
