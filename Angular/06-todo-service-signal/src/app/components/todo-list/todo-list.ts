import { Component, Signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoService } from '../../services/todo.service';
import { Todo } from '../../models/todo.model';

@Component({
  selector: 'app-todo-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './todo-list.html',
  styleUrls: ['./todo-list.css']
})
export class TodoListComponent {

  todos: Signal<Todo[]>;

  constructor(public todoService: TodoService) {
    // keep a reference to the signal from the service
    this.todos = this.todoService.todos;
  }

  onToggle(id: number): void {
    this.todoService.toggle(id);
  }

  onRemove(id: number): void {
    this.todoService.remove(id);
  }

  onClearAll(): void {
    this.todoService.clearAllTodos();
  }

}