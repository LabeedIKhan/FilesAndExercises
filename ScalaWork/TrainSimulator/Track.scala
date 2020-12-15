

class Track(
             override val length : Int,
             override val capacity: Int
           )extends Network("Track", length, capacity) {

  override  def toString() : String = {
     val display = "|___" + train(0) + "___|"
     display
  }

}
