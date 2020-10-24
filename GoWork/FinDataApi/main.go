package main


import (
	"fmt"
	"log"
	"net/http"
	"encoding/json"
	"os"
	"bufio"
	"strings"
)

type Data struct{
	Symbol  string  `json:"Symbol"`
	Name    string  `json:"Name"`
	Price   string  `json:"Price"`
}

type Profile struct {
	Symbol  	    string	  `json:"Symbol"`
	CompanyName     string	  `json:"Name"`
	Industry	    string	  `json:"Industry"`
	Sector   		string    `Json:"Sector"`
	Price    		string    `json:"Price"`
	MktCap          string    `json:"MKtCap"`
	PeRatio         string    `json:"PeRatio"`
	Description     string    `json:"Description"`
	WebSite         string    `json:"WebSite"`
	DateM           string    `json:"Date"`
}

type SymbolsList []Data
type ProfileList []Profile

func AllSymbols(w http.ResponseWriter, r * http.Request){
	

	data := ReadStoredData1()

	json.NewEncoder(w).Encode(data)
}

func SendProfile(w http.ResponseWriter, r * http.Request){

	
	data := ReadStoredData2()

	json.NewEncoder(w).Encode(data)
}

func homepage(w http.ResponseWriter, r * http.Request){
	fmt.Fprint(w , "Homepage")
}

func RequestHandler() {
	http.HandleFunc("/", homepage)
	http.HandleFunc("/profile", SendProfile)
	http.HandleFunc("/symbolist", AllSymbols)
	log.Fatal(http.ListenAndServe(":8080", nil))
}

func main(){
	RequestHandler()
}


func ReadStoredData1() []Data {

	var mainList []Data

	var alllines []string

	file, err := os.Open("SymbolsList.txt")

	if err != nil{
		fmt.Println(err)
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan(){
		st := scanner.Text()
		alllines = append(alllines, st)
	}

	for i := 0; i < len(alllines); i++ {
		ind := strings.Split(alllines[i], "|")
		d := Data{ ind[0], ind[1], ind[2] }
		mainList = append(mainList , d)
	}

	
	
	return mainList
}

func ReadStoredData2()[]Profile{

	var mainList []Profile

	file, err := os.Open("Profile.txt")

	if err != nil {
		fmt.Println(err)
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		st := scanner.Text()
		ind := strings.Split(st, ",")

		profile := Profile{ind[0], ind[1], ind[2], ind[3], ind[4], ind[5], ind[6], ind[7], ind[8], ind[9]}
		mainList = append(mainList, profile)
		
	}
	return mainList
}

