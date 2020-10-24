
package main

import (
	"fmt"
	"bufio"
	"os"
	
)

type Wine struct{
	winename string
	wineprice float64
	winequantity int
}



func (wine * Wine) Init(customer * Customer){

	scanner := bufio.NewScanner(os.Stdin)

	for {
	fmt.Println("Please Enter Wine Name")
	scanner.Scan()
	wine.winename = scanner.Text()
	if len(wine.winename) == 0  {
		break
	}
	
	fmt.Println("Please Enter wine price")
	fmt.Scanf("%f\n", &wine.wineprice)

	fmt.Println("Please Enter Wine Qaunitity")
	fmt.Scanf("%d\n", &wine.winequantity)

	totalvalue := totalVal(wine.wineprice, wine.winequantity)

	DoTransaction(totalvalue, customer)

	record = append(record, Wine{winename: wine.winename,wineprice: wine.wineprice, winequantity: wine.winequantity})
	}
}

func totalVal(price float64, quantity int) float64{

	total := price * float64(quantity)

	return total
}

func DoTransaction(totalvalue float64, customer * Customer){

	if totalvalue > 0{
	customer.balance += totalvalue
	}else{
	customer.balance -= totalvalue
	}
}