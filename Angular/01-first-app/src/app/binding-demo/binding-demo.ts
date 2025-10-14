import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-binding-demo',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './binding-demo.html',
  styleUrl: './binding-demo.css'
})
export class BindingDemo {

  //string interpolation
  name:string = "Ashish";

  counter:number = 0;

  incrementCounter(){
    if(this.counter<15){
      this.counter++;
    }
  }

  decrementCounter(){
    if(this.counter>0){
      this.counter--;
    }
  }

  //property binding
  active : boolean = true;

  toggleButton() {
    this.active = !this.active;
  }

  //image url binding
  imageUrl: string = 'https://media.istockphoto.com/id/1271072224/photo/hand-using-laptop-and-press-screen-to-search-browsing-on-the-internet-online.webp?a=1&b=1&s=612x612&w=0&k=20&c=69cBQpj7FhAtB7d4MjhnuneCdAKtWpH0KZs9Beww6oc=';

  //class binding
  isActive : boolean = true;

  isValid : boolean = false;

  //twoway binding
  userName : string = "";

  fontSize : number = 20;
  Math=Math

  bg = "yellow"
}