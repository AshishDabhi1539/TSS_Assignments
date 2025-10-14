import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MeterToKmPipe } from './meter-to-km.pipe';
import { BgColorDirective } from './bg-color.directive';

@Component({
  selector: 'app-root',
  imports: [CommonModule,MeterToKmPipe,BgColorDirective],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  
  name:string="Ashish Dabhi"

  val:number = 12123453.4561234567890

  score:number = 0.987654321

  num:number = 234.456

  today = new Date();

  username : string[] = ['Ashish','Chirag','Artik','Sumit','Dev','Neel']

  user: any = {
  name: 'Ashish Dabhi',
  email: 'ashish.dabhi@gmail.com',
  role: 'Developer',
  skills: ['Angular', 'Java', 'Spring Boot']
  }

  gender : 'male'|'female'|'other' = 'male';

  genderMap = {
    male:'Hello, Sir!',
    female:'Hello, Madam!',
    other:'Hello, there!'
  }

  message=2;

  messageMap = {
    '=0':"No Messages",
    '=1':"1 message",
    'other':'# Message'
  }

   distance:number = 4500;

   msg = "Hover or click me!";
}
