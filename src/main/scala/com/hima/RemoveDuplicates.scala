package com.hima

object RemoveDuplicates extends App {


  val listAdd = scala.io.Source.
    fromFile("src/main/resources/input_ds.csv")
    .getLines().drop(1)
    .foldLeft(Set.empty[String]) {
      case (seen, str) =>
        println("seen =>" + seen + "  str ==>" + str)
        val substr = str.take(str.lastIndexOf(","))
        if (!seen(substr)) println(str)
        seen + substr
    }

  //println("final ==> "+listAdd)


}
