package com.hima.rest

import com.hima.Main.args
import java.io._
import com.hima.{Main, humanFriendlyTransformation}
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

@RestController
class TimeRestController {



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
