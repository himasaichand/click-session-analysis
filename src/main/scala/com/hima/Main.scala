package com.hima

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Main extends App {


  if (args.length == 0) {
    println(LocalDateTime.now())
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val ht = formatter.format(LocalDateTime.now())
    println(new humanFriendlyTransformation().human_transform(ht))
  }
  else {
    if (args.length > 1) {
      throw new IllegalArgumentException("Accepts one or none argument ")
    }
    else {

      println(new humanFriendlyTransformation().human_transform(args(0)))
    }


  }


}
