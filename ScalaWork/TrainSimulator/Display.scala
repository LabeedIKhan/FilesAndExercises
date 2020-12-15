import java.util.concurrent.locks.ReentrantLock

class Display(val start : Network) extends Runnable {

  var lock = new ReentrantLock()
  var lock1 = new ReentrantLock()

  override def run(): Unit = {
    rundisplay()
  }

  def rundisplay(): Unit = {

    lock1.lock()
    try{
      Thread.sleep(1000)
      var thistrack = start

    }catch{
      case e : InterruptedException => ???
      case _ : Throwable => ???
    }
    lock1.unlock()
  }

  def showdisplay(track : Network): Unit = {
      lock.lock()
      var display = ""
      display += track
    if (track.nextSegment != None){
      println(track)
      showdisplay(track.nextSegment)
    }else{
      lock.unlock()
    }

  }

}
