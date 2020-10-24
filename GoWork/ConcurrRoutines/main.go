package main

import (
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
)

type Urls struct {
	
	url1  string
	url2 string
	url3 string
}


func main(){

	link := Urls{
		url1:  "https://www.url1.com",
		url2:  "https://www.url2.com",
		url3:  "https://www.url3.com",
	}

	c1 := make(chan string)
	c2 := make(chan string)
	c3 := make(chan string)

	go GetHttp1(c1, link.url1)
	go GetHttp2(c2, link.url2)
	go GetHttp3(c3, link.url3)

	res1, res2, res3 := <-c1, <-c2, <- c3

	comres := []string {res1, res2, res3}

	for _,val := range comres{
		fmt.Println(val)
	}

	close(c1)
	close(c2)
	close(c3)

	fmt.Scanln()
}

func GetHttp1(str chan string, url string){

	fmt.Println("Sending Request to" + url)
	response, err := http.Get(url)

	if err != nil {
		log.Fatal(err)
	}

	defer response.Body.Close()

	data, err := ioutil.ReadAll(response.Body)

	if err != nil {
		log.Fatal(err)
	}

	result := string(data)	

	str <- result
}

func GetHttp2(str chan <- string, url string){

	fmt.Println("Sending Request to" + url)
	response, err := http.Get(url)

	if err != nil {
		log.Fatal(err)
	}

	defer response.Body.Close()

	data, err := ioutil.ReadAll(response.Body)

	if err != nil {
		log.Fatal(err)
	}

	result := string(data)

	str <- result
}

func GetHttp3(str chan <- string, url string){

	fmt.Println("Sending Request to" + url)
	response, err := http.Get(url)

	if err != nil {
		log.Fatal(err)
	}

	defer response.Body.Close()

	data, err := ioutil.ReadAll(response.Body)

	if err != nil {
		log.Fatal(err)
	}

	result := string(data)

	str <- result
}


