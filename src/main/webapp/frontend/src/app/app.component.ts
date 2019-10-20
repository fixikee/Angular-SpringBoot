import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

	roomsearch : FormGroup;
	rooms: Room[];
	public submitted: boolean;
		
    ngOnInit(): void {
		this.roomsearch = new FormGroup({
			checkin : new FormControl(''),
			checkout: new FormControl('')
		});
		
		this.rooms = ROOMS;
    }

	onSubmit({value, valid} : {value: Roomsearch, valid: boolean}) {
		console.log(value);
	}
	
	reserveRoom(value: string) {
		console.log("Room id: " + value);
	}
}

export interface Roomsearch {
	checkin: string;
	checkout: string;
}

export interface Room {
	id: string;
	roomNumber: string;
	price: string;
	links: string;
}

var ROOMS: Room[] = [
	{
		"id" : "3456",
		"roomNumber" : "409",
		"price" : "20",
		"links": ""
	},
	{
		"id" : "1234",
		"roomNumber" : "410",
		"price" : "25",
		"links": ""
	},
	{
		"id" : "7891",
		"roomNumber" : "411",
		"price" : "30",
		"links": ""
	}
]