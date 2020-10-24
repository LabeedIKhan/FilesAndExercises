package main


import "fmt"
import "strconv"


var record []Wine

func main(){
	//Test()
	
	customer := new(Customer)
	customer.Init()

	fmt.Println("Customer Name is " + customer.name + "Customer Balance is " + strconv.FormatFloat(customer.balance, 'f', 2, 64))

	wine := new(Wine)
	wine.Init(customer)
 
	PerformExitTask(customer,wine)
}


func PerformExitTask(customer * Customer, wine * Wine){
	 
    receipt := ""
	receipt += "Customer Name is " + customer.name + "Customer Balance is " + strconv.FormatFloat(customer.balance, 'f', 2, 64)  + " \n"
	
	for index,_ := range record {
		
		receipt += "Wine Name is : " + record[index].winename + "Wine Price is : "+ strconv.FormatFloat(record[index].wineprice, 'f', 2, 64) +"Wine Quantity is : "+ strconv.Itoa(record[index].winequantity) + "\n"
		index++
	}

	fmt.Println(receipt)
	//SetDisplay(receipt)
}



