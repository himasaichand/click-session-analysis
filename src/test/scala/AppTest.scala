import com.fasterxml.jackson.databind.JsonNode
import com.hima.TimeAppMapper
import com.hima.rest.TimeRestController
import org.junit.jupiter.api.Test
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.client.RestTemplate


@ActiveProfiles(profiles = Array("test"))
@SpringBootTest(classes = Array(classOf[TimeAppMapper]))
class AppTest extends AnyFlatSpec with Matchers {

 val baseUrl="http://localhost:8080/"
  val restTemplate : RestTemplate=null
  "Autowired properties" should "be non-null" in {
    @Test
    def testTime() = {
      val body = restTemplate.getForObject("/now", classOf[JsonNode])
      fail("not yet implemneted")
    }

  }


  @Test
  def testStuff(): Unit  ={


   var reply=restTemplate.getForObject(baseUrl,classOf[TimeRestController].getClass)
    assert(reply.toString.isEmpty)
  }

}
