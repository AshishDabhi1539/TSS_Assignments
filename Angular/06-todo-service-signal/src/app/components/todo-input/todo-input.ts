import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-todo-input',
  standalone:true,
  imports: [FormsModule,CommonModule],
  templateUrl: './todo-input.html',
  styleUrl: './todo-input.css'
})
export class TodoInput {

  //dependency injection of TodoService
  constructor(private todoService:TodoService){}

  title='';

  onAdd(f:NgForm){
    const tempTitle = this.title.trim();
    if(!tempTitle) return;
    this.todoService.add(tempTitle);
    this.title='';    
  }
  
}
