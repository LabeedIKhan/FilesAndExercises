

object Main extends App{

  val station = new Network("London", 1000, 5)

  val track = new Track(1000,1)

  val station1 = new Network("Birmigham", 1000, 4)

  val track1 = new Track(1000,1)

  val station2 = new Network("Manchester", 1000, 6)


  station.setnext(track)

  track.setnext(station1)

  station1.setnext(track1)

  track1.setnext(station2)

  station2.setnext(new TrackEnd)

  new Thread(new Factory(station)).start()

  new Thread(new Display(station)).start()
}
