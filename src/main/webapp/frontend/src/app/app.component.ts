import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
//import { catchError } from 'rxjs/operators/';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
	
	constructor(private http: Http){}

	roomsearch : FormGroup;
	rooms: Room[];
	public submitted: boolean;
	private baseUrl: string = 'http://localhost:8080';
		
    ngOnInit(): void {
		this.roomsearch = new FormGroup({
			checkin : new FormControl(''),
			checkout: new FormControl('')
		});
    }

	onSubmit({value, valid} : {value: Roomsearch, valid: boolean}) {
		this.getAll().subscribe(
			rooms => this.rooms = rooms,
			err => {
				console.log(err);
				}
			);
	}
	
	reserveRoom(value: string) {
		console.log("Room id: " + value);
	}
	
	getAll(): Observable<Room[]> {
		return this.http.get(this.baseUrl + '/room/reservation/v1?checkin=2017-03-18&checkout=2017-03-20').pipe(map(this.mapRoom));
	}
	
	mapRoom(response: Response): Room[] {
		return response.json().content;
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