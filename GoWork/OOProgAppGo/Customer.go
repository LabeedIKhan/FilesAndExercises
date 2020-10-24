
package main

import (
    "bufio"
    "fmt"
	"os"
	
)

type Customer struct{
	name string 
	balance float64
}

func (customer * Customer) Init() {
 
	reader := bufio.NewReader(os.Stdin)
	fmt.Println("Please Enter Customer Name")
	input,_ := reader.ReadString('\n')
	customer.name = input
	
	fmt.Println("Please Enter Customer balance")
	fmt.Scanf("%f\n", &customer.balance)
}