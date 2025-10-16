import { Component, inject } from '@angular/core';
import { TodoInput } from './components/todo-input/todo-input';
import { TodoListComponent } from './components/todo-list/todo-list';
import { TodoService } from './services/todo.service';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [TodoInput,TodoListComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  todoService = inject(TodoService);
}
