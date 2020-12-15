import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Map
import scala.collection.mutable.Set


object Collectionscala {

  def main(args : Array[String])= {
    RunArrayBuffer ()
    RunList()
    RunVector()
    RunMaps()
    RunSets()
    RunMapsFilters()
  }


  def RunArrayBuffer() = {
    val myints = ArrayBuffer[Int]()

    val mydou = ArrayBuffer[Double]()

    val mystr = ArrayBuffer[String]()

    mystr += "Labeed"
    mystr += "Imran"
    mystr += "Khan"

    for (n <- mystr) printf("%s ", n);
    println()
    mystr -= "Imran"
    for (n <- mystr) printf("%s ", n);
    println()
    mystr -= "Khan"
    for (n <- mystr) printf("%s ", n);
    println()

    val name = ArrayBuffer("Labeed", "Imran", "Khan")
    for (n <- name) println(n)
    name ++= List("Is", "Present")
    for (n <- name) println(n)
    name --= List("Is", "Present")
    for (n <- name) println(n)

    val a = ArrayBuffer(1, 10)
    a.append(4)
    a.appendAll(Seq(5, 6))
    a.prepend(20)
    a.insert(1, 111)
    for (item <- a) print(item)

    val rangea = ArrayBuffer.range(1, 10)
    println()
    rangea.insertAll(9, Seq(10, 11, 12))
    rangea.trimEnd(3)
    for (item <- rangea) print(item);
    print(" ")
  }

  def RunList(): Unit = {

    val list = List("Name1", "Name2", "Name3")

    val list1 = list :+ "Name4"
    val list2 = "Name0" +: list1

    for ( n <- list2) println(n)

    val list4 = for(n <- list2) yield n

    for ( n <- list4) println(n)

    val intlist = List(1, 2, 3)

    val intlist1 =  intlist :+ 4

    val doublelist = intlist1 :++ List(5, 6, 7)

    for ( n <- doublelist) println(n)

    val listend = 1 :: 2 :: 3 :: 4 :: Nil

    for ( n <- listend) println(n)
  }

  def RunVector():Unit = {

    val nums = Vector(1, 2, 3)

    val nums1 = nums :++ Vector(4, 5, 6)

    val nums2 = nums1 :+ 7

    val nums0 = 0 +: nums2

    val numfull = List(-3, -2, -1) ++: nums0

    for ( n <- numfull) println(n)

    val names0 = Vector[String]()

    val names1 = names0 :+ "name1"

    val names2 = names1 :++ List("name2", "name3")

    val names3 = "name0" +: names2

    val names4 = List("name-3", "names-2", "names1")

    val names5 =  names4 ++: names3

    for ( n <- names5) println(n)

    val finalnames = for ( n <- names5) yield n

    for ( n <- finalnames) println(n)
  }

  def RunMaps() : Unit = {

    var fmap = Map[Int, String]()

    fmap += (1 -> "Pakistan")

    fmap ++= Map(2 -> "South Africa", 3 -> "Afghanistan")

    fmap ++= Map(3 -> "India", 4 -> "Sri lanka", 5 -> "Bangladesh", 6 -> "Thailand")

    fmap ++= Map(7 -> "UK", 8 -> "Usa")

    fmap -= 5

    fmap --= List(7, 8)

    fmap(3) = "India - Huge Population"

    fmap(4) = "Sri Lanka - Small Island"

    for ( (k , v) <- fmap) println(s"The Number is $k and country is $v")

    fmap.foreach {
      case (key , values) => println(s"The key is $key and country is $values")
    }

  }

  def RunSets(): Unit = {
    val set = Set[String]()

    set += "name1"

    set += "name2"

    set ++= List("Name3", "name4", "name4", "name5")

    set ++= Vector("Name6", "Name7")

    set += "NameR"

    set -= "NameR"

    set --= Vector("Name6", "Name7")

    set.add("Aname1")

    set.addAll(List("Aname2", "Aname9"))

    set += "AnameA1" += "AnameA2"

    for ( s <- set) println(s)
    println("===")

    set -= "AnameA1" -= "AnameA2"

    for ( s <- set) println(s)
    println("===")

    set.remove("name4")

    for ( s <- set) println(s)

    val removed = set.remove("name2")

    println(removed)

    val removed1 = set.remove("AnameA1")

    println(removed1)

    set.clear()

    println("===")

    for ( s <- set) println(s)
  }

  def RunMapsFilters(): Unit = {

    val list = List.range('A', 'Z')

    for ( l <- list) {
      print(l)
      print(" ")
    }

    val ints = List.range(1, 10)
    val dints = ints.map(_ * 3)
    println()

    for ( l <- dints) {
      print(l)
      print(" ")
    }

    val ddints = ints.map( (i : Int) => i * 2)

    println()
    for ( l <- ddints) {
      print(l)
      print(" ")
    }

    val dddints = ints.map(i => i + 3)

    println()
    for ( l <- dddints) {
      print(l)
      print(" ")
    }

    val randint = List.range(20, 50)
    val lrandint = randint.filter(_ > 40)

    println()
    for ( l <- lrandint) {
      print(l)
      print(" ")
    }

    val srandint = randint.filter(_ < 30)

    println()
    for ( l <- srandint) {
      print(l)
      print(" ")
    }

    val allnum = List.range(1, 10)
    val evennums = allnum.filter(_ % 2 == 0)

    println()
    for ( l <- evennums ) {
      print(l)
      print(" ")
    }

    val oddnums = allnum.filter((i : Int) => i % 2 != 0)

    println()
    for ( l <- oddnums ) {
      print(l)
      print(" ")
    }

    val oddnums1 = allnum.filter(i => i % 2 != 0)

    println()
    for ( l <- oddnums1 ) {
      print(l)
      print(" ")
    }
  }

}
