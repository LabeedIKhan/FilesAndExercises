
import scala.util.Random

class Factory(next : Network) extends Network("",0,0) with Runnable {

  val randomnumber = new Random(1)
  val randomtrain = new Random(50)
  val randomspeed = new Random(100)

  setnext(next)

  override def run(): Unit = {
    try{
      starttrain()
    }catch {
      case e : InterruptedException => println("interupted")
      case _ : Throwable => println("unknown")
    }

  }

  def starttrain(): Unit = {

    Thread.sleep(1000)
    val speed = randomspeed.nextInt()
    val newtrain = new Train(this, speed, randomtrain.toString)
    new Thread(newtrain).start()
    starttrain()
  }

}
