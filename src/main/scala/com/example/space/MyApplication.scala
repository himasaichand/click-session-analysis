package com.example.space
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MyApplication {

  @Bean
  def mapper : ObjectMapper = {
    JsonMapper.builder()
      .addModule(DefaultScalaModule)
      .build()
  }
}
