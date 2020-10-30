package main

import(
	"fmt"
)

type I interface{}

type Pair struct{
	key   I
	value I
}

func RunPair(){

	list := []Pair{}

	p := Pair{"One", 1}
	list = append(list, p)

	p1 := Pair{1, "Second"}
	list = append(list, p1)

	p2 := Pair{1.2, "Third"}
	list = append(list, p2)

	p3 := Pair{2.3, 85.2}
	list = append(list, p3)

	p4 := Pair{2.6, true}
	list = append(list, p4)

	for i,val := range list{
		fmt.Println(i, val.key , val.value)
	}

}

func MyListIterator(list* []string){

	fmt.Println("\nUsing My List Iterator")
	for _,val := range *list{
		fmt.Println(val)
	}
}

func MyEnhancedForLoop(list * []string){

	fmt.Println("\nThis is from my Enhanced Loop")
	
	AddToList := func(arr[] string, index int, value string) []string{

		arr = arr[0 : len(arr) + 1]
		copy(arr[index+1:], arr[index:])
		arr[index] = value
		return arr
	}

	*list = AddToList(*list,0, "Peterhead Prison Museum")
	*list = AddToList(*list,1, "Wick Heritage Museum")

	for _,val := range *list{
		fmt.Println(val)
	}
}

func MyWhileLoop(list * []string){

	fmt.Println("\nThis is from my while loop")
	torem := "Camera Obscura and World of Illusions"

	var newlist []string

	for _,val := range *list{
		if val == torem{
			continue
		}else{
			newlist = append(newlist, val)
		}
	}


	for _, val := range newlist {
		fmt.Println(val)
	}

}

func main(){

	var list []string

	list = append(list, "Kelvingrove Art Gallery and Museum")
	list = append(list, "National Museum of Scotland")
	list = append(list, "Royal Yacht Britannia")
	list = append(list, "Camera Obscura and World of Illusions")
	list = append(list, "Morayvia")
	list = append(list, "Montrose Air Station Heritage Centre")

	for i,v := range list{
		fmt.Println(i, v)
	}

	MyListIterator(&list)
	MyEnhancedForLoop(&list)
	MyWhileLoop(&list)
	RunPair()

}