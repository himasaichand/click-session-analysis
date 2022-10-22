package com.example.space.rest
import com.example.space.Main.args
import java.io._
import com.example.space.{Main, humanFriendlyTransformation}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

@RestController
//@RequestMapping(path = Array("/time"))
class SpaceRestController {

/*
  @GetMapping(path = Array("/currentTime"))
  def myship()  = {

    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val ht = formatter.format(LocalDateTime.now())

   new humanFriendlyTransformation().human_transform(ht)
  }

  @GetMapping(path = Array("/userDefinedTime"))
  def myship1(@RequestParam(required = false)  name:String) = {



     new humanFriendlyTransformation().human_transform(name);
  }
*/

  @GetMapping(path = Array("/userDefinedTime1"))
  def myship3(@RequestParam(required = false) name: String) = {
    var ht=""
    var result=""
    if (name==null) {
      val formatter = DateTimeFormatter.ofPattern("HH:mm")
      ht = formatter.format(LocalDateTime.now())
    }
    else{
      ht=name
    }
    try {
     new humanFriendlyTransformation().human_transform(ht)
    }
    catch {

      case _: Throwable => println(new humanFriendlyTransformation().human_transform(ht))
    }

  }

}
