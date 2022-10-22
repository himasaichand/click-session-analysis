import org.scalatest.{FlatSpec, Matchers, TryValues}
import  com.hima.humanFriendlyTransformation
import com.hima.Main

class ClockTest extends FlatSpec with Matchers with TryValues {

  val hft= new humanFriendlyTransformation

  assert(hft.human_transform("1:00")==="1:00 One o'clock")
  assert(hft.human_transform("2:00")==="2:00 Two o'clock")
  assert(hft.human_transform("13:00")==="13:00 One o'clock")
  assert(hft.human_transform("13:05")==="13:05 Five past one")
  assert(hft.human_transform("13:10")==="13:10 Ten past one")
  assert(hft.human_transform("13:25")==="13:25 Twenty five past one")
  assert(hft.human_transform("13:30")==="13:30 Half past one")
  assert(hft.human_transform("13:35")==="13:35 Twenty five to two")
  assert(hft.human_transform("13:55")==="13:55 Five to two")


  the [IllegalArgumentException] thrownBy(Main.main(Array("123:56"))) should have message "Time format is not valid. Usage format -> HH:MM or H:MM "
  the [IllegalArgumentException] thrownBy(Main.main(Array("66:16"))) should have message "Invalid hour. Usage --> value should be in 0 to 23"
  the [IllegalArgumentException] thrownBy(Main.main(Array("11:63"))) should have message "Invalid minutes. Usage --> value should be in 0 to 59"
  the [IllegalArgumentException] thrownBy(Main.main(Array("10:7"))) should have message "Time format is not valid. Usage format -> HH:MM or H:MM "


}