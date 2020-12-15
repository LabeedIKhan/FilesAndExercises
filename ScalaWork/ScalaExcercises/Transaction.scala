import scala.util.control.Breaks.break
import scala.collection.mutable.ArrayBuffer
import java.io._

object Transaction {

    def main(args : Array[String]): Unit= {
        
        while(true){
            println("Please Select if you want to continue the transaction")
            println("enter c if to continue or else enter e")

            val input = io.StdIn.readLine()

            if ((input == "C") || (input == "c")){
                Transaction()
            }else{
                println("You exited the application")
                break
            }

        }
    }

    def Transaction(): Unit ={
        val customername = CustomerName()
        val customerbalance  = CustomerBalance()
        println("Customer Name is", customername)
        println("Customer Balance is", customerbalance)
        var record = new ArrayBuffer[String]()
        record.append(customername + ": " + customerbalance.toString)
        record.insertAll(1, GetWineTransactions(customerbalance))
        CompleteOperations(record)
    }

    def CustomerName(): String = {
        println("Please Enter Customer name");
        val name = io.StdIn.readLine()
        name
    }

    def CustomerBalance(): Double = {
        println("Please Enter Customer balance");
        val balance = io.StdIn.readLine()
        val balanced = balance.toDouble
        balanced
    }

    def GetWineTransactions(customerbalance : Double) : ArrayBuffer[String] = {

        val alltransaction = new ArrayBuffer[String]()
        var balance = customerbalance
        var cond = true
        while(cond) {

            println("Please Enter Wine Name")
            val winename = io.StdIn.readLine()

            if (winename != "") {

                println("Please Enter Wine Quantity")
                val winequantity = io.StdIn.readLine()
                println("Please Enter Wine Price")
                val wineprice = io.StdIn.readLine()

                balance = PerformTransaction(balance, winequantity.toInt, wineprice.toDouble)

                alltransaction.append("Wine Name " + winename + " Wine Quanity " + winequantity + " Wine Price " + wineprice + " Updated Balance " + balance.toString)
            }else{
                cond = false
            }
        }
        alltransaction
    }

    def PerformTransaction(balance : Double, quantity : Int, price : Double): Double = {

        val totalval = quantity * price
        var balanceaftertransaction = balance
        if (quantity > 0){
            balanceaftertransaction -= totalval
        }else{
            balanceaftertransaction += totalval
        }
        balanceaftertransaction
    }

    def CompleteOperations(data : ArrayBuffer[String]): Unit = {

        println("Please Specify Operation P for console printing f for file storing")
        val input  = io.StdIn.readLine()

        if (input == "P" || input == "p"){
            for(line <- data){
                println(line)
            }
        }else{
            println("Please Write Name of file")
            val filename = io.StdIn.readLine()
            Writefile(filename, data)
        }
    }

    def Writefile(filename : String , data : ArrayBuffer[String]): Unit = {

      val bufferwrite = new BufferedWriter(new FileWriter(new File(filename)))
      try {
        for (line <- data) {
          bufferwrite.write(line + "\n")
        }
      } catch  {
         // case e: FileNotFoundException => println("Couldn't find that file.")
         // case e : IOException => println("Unable to Write File")
          case _: Throwable => println("Exception")
      } finally {
        bufferwrite.close()
      }
    }
}
