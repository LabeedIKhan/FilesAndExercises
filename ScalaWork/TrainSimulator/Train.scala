


class Train(
           var thistrack : Network,
           var speed : Int,
           var number : String
           ) extends Runnable{

  var nexttrack : Network = ???

  override def run(): Unit = {
    runloop()
  }

    def runloop(): Unit = {
      val sleeptime = thistrack.length / speed

      try {
      Thread.sleep (sleeptime)
    } catch {
      case e: InterruptedException => println("Interuppted")
      case _: Throwable => println("unknown exception")
    }
      if (nexttrack == None) {
      thistrack.removeTrain (this)
      return
    }

      nexttrack.enterTrain (this)
      thistrack.removeTrain (this)
      thistrack = nexttrack

      runloop()
    }

  override def toString() : String = {
    this.number
  }

}
