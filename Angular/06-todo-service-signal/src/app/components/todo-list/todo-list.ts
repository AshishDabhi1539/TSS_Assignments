import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Todo } from '../../models/todo.model';
import { TodoService } from '../../services/todo.service';

@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './todo-list.html',
  styleUrls: ['./todo-list.css']
})
export class TodoList implements OnInit {

    todos: Todo[]=[];
 
  todoService = inject(TodoService);

  @Input({ required: true }) todosInput: Todo[] = [];
  @Output() toggleEmitter = new EventEmitter<number>();
  @Output() deleteEmitter = new EventEmitter<number>();

  
  ngOnInit(): void {
    // Initialize todos from the service signal
    this.todos = this.todoService.todos();
  }

  toggleTodo(id: number) {
    this.toggleEmitter.emit(id);
  }

  deleteTodo(id: number) {
    this.deleteEmitter.emit(id);
  }

  get hasTodos(): boolean {
    return this.todos.length > 0;
  }
}
