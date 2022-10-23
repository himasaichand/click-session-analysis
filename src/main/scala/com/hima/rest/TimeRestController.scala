package com.hima.rest

import com.hima.humanFriendlyTransformation
import org.springframework.web.bind.annotation.{GetMapping, RequestParam, RestController}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
class TimeRestController {


  @GetMapping(path = Array("/now"))
  def timeRest(@RequestParam(required = false) time: String) = {
    var ht = ""
    if (time == null) {
      val formatter = DateTimeFormatter.ofPattern("HH:mm")
      ht = formatter.format(LocalDateTime.now())
    }
    else {
      ht = time
    }
    try {
      new humanFriendlyTransformation().human_transform(ht)

    }
    catch {
      case e: Exception =>
        e.getLocalizedMessage
    }

  }


}
