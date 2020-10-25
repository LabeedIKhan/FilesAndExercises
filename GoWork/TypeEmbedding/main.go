package main


import (
	"fmt"
	//"os"
	//"bufio"
)

type Aircraft struct{
	maxspeed          int
	maxrange  		  int
	capacity 		  int
	typeofaircraft    string
}

func (aircraft * Aircraft) AircraftToString() string{
	return	fmt.Sprintf("%s: %d %s: %d %s: %d %s: %s",
	 					"Speed", aircraft.maxspeed,
						"Range",aircraft.maxrange,
						"Capacity",aircraft.capacity,
						"Type",aircraft.typeofaircraft)
}

func (aircraft * Aircraft) TypeDescription() string{

	if aircraft.typeofaircraft == "Helicopter"{
		return "Rotary-Wing"
	}else if aircraft.typeofaircraft == "Airplane"{
		return "Fixed-Wing"
	}else{
		return "Unkwon Type"
	}
}

type Airplane struct{
	seats 			int
	propulsion      string
	ismilitary      bool
	Aircraft
}

func (airplane * Airplane) AirplaneToString()  string{
	return fmt.Sprintf("%s %s: %d %s: %s %s: %t %s",
		        airplane. AircraftToString(), 
				"Seats", airplane.seats , 
				"Propulsion", airplane.propulsion, 
				"Is Military",airplane.ismilitary, 
				airplane.TypeDescription())
}

type FighterJet struct{
	issupersonic bool
	Airplane
}

func (fighter * FighterJet) GetFighterJetDescription() string{
	return fmt.Sprintf("%s %t",
				fighter.AirplaneToString(), 
				fighter.issupersonic )
}

type TransportHelicopter struct{
	Airplane
	Aircraft
}

func (th * TransportHelicopter) GetTransportHelicopterDesc() string{
	return fmt.Sprintf("%s %s",th.AirplaneToString(), th.AircraftToString())
}

func (th * TransportHelicopter) IsHelicopterMilitary() bool {
	return th.ismilitary;
}

func (th * TransportHelicopter) SetTransportHelicopterType(t  bool){
	th.ismilitary = t
}


func main(){

	aircraft := Aircraft{ 100 , 1000 , 200 , "Helicopter", }

	airplane := Airplane{200, "turboshaft", false, aircraft,}

	fmt.Println()

	fmt.Printf("%s: %s\n","Description",airplane.AirplaneToString())

	airctypedes := airplane.TypeDescription()

	fmt.Printf("\n%s: %s\n","Type Description",airctypedes)

	fmt.Println()

	fighteraircraft := Aircraft{2200 , 1500 , 2 , "Airplane",}

	fighterplane := Airplane{200, "turbojet", true, fighteraircraft,}

	fighter := FighterJet{true, fighterplane,}

	fmt.Printf("%s \n", fighter.GetFighterJetDescription())

	helicraft:= Aircraft{300 , 1500 , 7 , "Helicopter",}

	heliplane := Airplane{200, "turboshaft", true, helicraft,}

	helicopter := TransportHelicopter{heliplane, helicraft}

	fmt.Printf("\n%s",helicopter.GetTransportHelicopterDesc())

	heliismilitary := helicopter.IsHelicopterMilitary()

	fmt.Printf("\n%t", heliismilitary)
}