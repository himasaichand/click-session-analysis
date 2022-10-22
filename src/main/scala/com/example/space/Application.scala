package com.example.space

import org.springframework.boot.SpringApplication




object Application  {
    def main(args: Array[String]): Unit = {
        SpringApplication.run(classOf[MyApplication], args :_ *)
    }

}

