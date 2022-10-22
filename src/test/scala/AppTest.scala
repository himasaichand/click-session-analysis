import com.hima.TimeAppMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.{ActiveProfiles, ContextConfiguration}

@ActiveProfiles(profiles=Array("test"))
@SpringBootTest(classes = Array(classOf[TimeAppMapper]))
class AppTest(
               @Value("${mygreeting}")
               greeting: String
             ) {



  @Test
  def testStuff(): Unit ={
    println(s"This is result $greeting")
  }

}
