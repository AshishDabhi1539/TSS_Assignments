import { Component, Input, Output, EventEmitter, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Todo } from '../../models/todo.model';
@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './todo-list.html',
  styleUrls: ['./todo-list.css']
})
export class TodoList {
  todos = input.required<Todo[]>(); // signal-style Input
  deleteEmitter = output<number>();
  toggleEmitter = output<number>();
}
