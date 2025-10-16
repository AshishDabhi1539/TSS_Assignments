import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoInput } from './components/todo-input/todo-input';
import { TodoList } from './components/todo-list/todo-list';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [TodoInput,TodoList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  
}
