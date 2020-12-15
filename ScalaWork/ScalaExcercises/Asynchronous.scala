import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure,Success}
import scala.io.Source.fromURL

object Asynchronous extends App {

  val url1 = "https://www.url1.com"
  val url2 = "https://www.url2.com"
  val url3 = "https://www.url3.com"

  val data1 : Future[String] = getdata(url1)
  val data2 = getdata(url2)
  val data3 = getdata(url3)

  val result: Future[(String, String, String)] = for {
    appl <- data1
    timese <- data2
    applmet <- data3
  } yield (appl, timese, applmet)

  result.onComplete {
    case Success(res) => {
      println(s"result is \n $res")
    }
    case Failure(e) => e.printStackTrace()
  }

  sleep(5000)

  def sleep(sleeptime: Long) : Unit = Thread.sleep(sleeptime)

  def getdata(url : String) : Future[String] = Future {
    println(s"Sending requrest to $url")
    val json = fromURL(url).mkString
    json
  }
}
