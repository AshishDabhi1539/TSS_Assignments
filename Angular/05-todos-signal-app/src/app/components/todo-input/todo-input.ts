import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-todo-input',
  standalone:true,
  imports: [FormsModule],
  templateUrl: './todo-input.html',
  styleUrl: './todo-input.css'
})
export class TodoInput {
title:string=''

  @Output() addEmitter = new EventEmitter<string>();

  onAdd(f:NgForm){
    console.log(this.title)
    this.addEmitter.emit(this.title)
    //this.title=''
    f.reset()
  }

}
