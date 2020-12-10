package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func RunMainCo(){

	url1 :=  "https://www.url1.com"
	url2 :=  "https://www.url2.com
	url3 :=  "https://www.url3.com
	
	c1 := make(chan string)
	c2 := make(chan string)
	c3 := make(chan string)

	e1 := make(chan error)
	e2 := make(chan error)
	e3 := make(chan error)

	go GetData(c1, url1, e1)
	go GetData(c2, url2, e2)
	go GetData(c3, url3, e3)

	res1, err1, res2, err2, res3 , err3 := <-c1, <- e1, <- c2, <- e2, <- c3, <- e3

	allerr := []error{err1,err2,err3}

	for i,v := range allerr{
		fmt.Println(i)
		fmt.Println(v)
	}

	allres := []string{res1, res2, res3}

	for _,val := range allres {
		fmt.Println(val)
	}

	fmt.Scanln()
}

func GetData(str chan <- string, url string, e chan <- error){

	fmt.Println("Sending Request To " + url)

	response , err := http.Get(url)

	if err != nil {
		 fmt.Println(err)
		 e <- err
	}
	defer response.Body.Close()

	data, err := ioutil.ReadAll(response.Body)
	
	if err != nil {
		fmt.Println(err)
		e <- err
	}

	str <- string(data)
	e <- nil
}