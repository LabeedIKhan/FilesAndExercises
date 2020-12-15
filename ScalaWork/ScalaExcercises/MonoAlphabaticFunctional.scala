
import java.io.{BufferedReader, BufferedWriter, File, FileNotFoundException, FileReader, FileWriter}
import scala.collection.mutable.ArrayBuffer


object MonoAlphabaticFunctional {

  def GetCharList =
    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789"

  def main(args : Array[String]): Unit = {

    def run() : Unit = {
      println("Please Enter Type of Encrypted m for Mono Alphabatic v for vignere or e for exit")
      val typechoice = scala.io.StdIn.readLine()
      typechoice match {
        case "V" | "v" => RunVignere()
        case "M" | "m" => RunMonoAlphabatic()
        case "E" | "e" => println("You Exited")
        case _ => {
          println("Please Enter Valid Input")
          run()
        }
      }
    }
    run()
  }

  def RunVignere() : Unit = {
    val key = GetKey()
    if (key == "end_") return
    val cipher = GetCipher(key)
    println(cipher)
    val filename = GetFilePath()
    if (filename == "end_") return
    val text = GetFileLines(filename)
    println(text)
  }

  def RunMonoAlphabatic() : Unit = {
    val key = GetKey()
    if (key == "end_") return
    val cipher = GetCipher(key)

    println("Cipher is " + cipher)
    val filename = GetFilePath()
    if (filename == "end_") return
    val text = GetFileLines(filename)

    val encrypted = MonoAlphabaticEncryption(text,cipher)
    println(encrypted)
    val dec = MonoAlphabaticDecryption(encrypted, cipher)
    println(dec)

  }

  def GetKey() : String = {
    println("Please Enter Key with out repeated characters or exit_ to exit")
    val key = io.StdIn.readLine()
    key match{
      case "end_"  => return "end_"
      case "exit_" => System.exit(0)
      case _ =>
    }
    val red = CheckRedundentChars(key)
    red match {
      case true => key
      case false => {
        println("Please Enter Correct key without redundant characters or enter end_ to end operation")
        println("key must be three char long")
        GetKey()
      }
    }

  }

  def CheckRedundentChars(key : String, counter : Int = 0) : Boolean = {

    if ( key.length < 3) return false
    if ( key.length == counter) return true
    val char = key(counter)
    val list : List[Char] = key.toList
    val red = list.filter(_ == char )
    if (red.size > 1) return false
    CheckRedundentChars(key, counter + 1)
  }

  def GetCipher(str: String,cip : String = "", counter : Int = 0): String = {

    if ( counter == 0) {
      val charlist: String = GetCharList
      val newchar = charlist.filter(_ != str(counter))
      GetCipher(str, newchar, counter+1)
    }else{
      if (counter == str.length) return str.concat(cip)
      val charlist = cip
      val newchar = charlist.filter(_ != str(counter))
      GetCipher(str, newchar, counter+1)
    }
  }


  def GetFileLines(filename : String): String = {
    val arraybuff = ArrayBuffer[String]()
    val source = io.Source.fromFile(filename)
    try {
      for (lines <- source.getLines()) arraybuff.append(lines)
    }catch{
      case _ : FileNotFoundException => println("file not found exception")
      case _ : Throwable => println("Unknown Error")
    }finally {
      source.close()
    }
    val text = arraybuff.mkString("\n")
    text
  }

  def GetFilePath(): String = {
    println("please enter path of file or end_ to go back")
    val input = io.StdIn.readLine()
    input match{
      case "end_" => "end_"
      case "" => GetFilePath()
      case _ => input
    }
  }

  def WriteFile(filename : String , data : ArrayBuffer[String]): Unit = {

    val bufferwrite = new BufferedWriter(new FileWriter(new File(filename)))
    try {
      for (line <- data) {
        bufferwrite.write(line + "\n")
      }
    } catch  {
      case _: Throwable => println("Exception")
    } finally {
      bufferwrite.close()
    }
  }

  def MonoAlphabaticEncryption(data : String, cipher : String, counter : Int = 0): String = {

    val alphabet = GetCharList
    if (counter == alphabet.length) return data
    val newdata = data.replace(alphabet(counter), cipher(counter))
    val newcounter = counter + 1
    MonoAlphabaticEncryption(newdata, cipher, newcounter)


  }

  def MonoAlphabaticDecryption(data : String, cipher : String, counter : Int = 0): String = {

    val alphabet = GetCharList
    if (counter == alphabet.length) return data
    val newdata = data.replace( cipher(counter), alphabet(counter))
    val newcounter = counter +1
    MonoAlphabaticDecryption(newdata, cipher, newcounter)
  }
}
