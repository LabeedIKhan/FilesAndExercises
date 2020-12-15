
import java.util.concurrent.locks.ReentrantLock
//import java.util.concurrent.locks.Condition
import scala.collection.mutable.ArrayBuffer

class Network(
             val name : String,
             val length: Int,
             val capacity: Int,
             ) {

  var nextSegment : Network = _
  var lock = new ReentrantLock()
  var cond = lock.newCondition()
  var train = new ArrayBuffer[Train]

  def enterTrain(t : Train): Unit = {
    lock.lock()
    while(train.size == capacity){
      try {
        cond.await()
      }catch{
        case e: InterruptedException => println("Interupted")
        case _ : Throwable => println("unknown error")
      }

    }
    train += t
    lock.unlock()
    cond.signalAll()

  }

  def removeTrain(t : Train) : Unit = {
    lock.lock()
    train -= t
    lock.unlock()
    cond.signalAll()
  }

  def setnext(segment : Network): Unit = {
    this.nextSegment = segment
  }

  override def toString() : String = {
    var display = "|____"
    for ( s <- train) display += s
    display += "____|"
    display
  }

}
