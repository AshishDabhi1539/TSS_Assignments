import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from '../../models/todo.model';

@Component({
  selector: 'app-todo-list',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './todo-list.html',
  styleUrl: './todo-list.css'
})
export class TodoList {
  @Input({required: true}) todosInput: Todo[] = [];
  @Output() toggleEmitter = new EventEmitter<number>();
  @Output() deleteEmitter = new EventEmitter<number>();

  toggleTodo(id: number) {
    this.toggleEmitter.emit(id);
  }

  deleteTodo(id: number) {
    this.deleteEmitter.emit(id);
  }
}
