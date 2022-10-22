package com.example.space

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



object Main extends App{



  if (args.length == 0) {
    println(LocalDateTime.now())
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val ht = formatter.format(LocalDateTime.now())
    println("time--> " + ht)
    println(new humanFriendlyTransformation().human_transform(ht))
  }
  else
    {
      if (args.length >1)
        {
         throw new IllegalArgumentException("Accepts one or none argument ")
        }
      else
        {
          //new humanFriendlyTransformation().CheckTimeFormat(args(0))
            println(new humanFriendlyTransformation().human_transform(args(0)))
        }


    }




}
