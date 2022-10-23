package com.hima

import org.springframework.boot.SpringApplication


object TimeRestAppMain {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[TimeAppMapper], args: _ *)
  }

}

