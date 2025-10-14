import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoInput } from './components/todo-input/todo-input';
import { TodoList } from './components/todo-list/todo-list';
import { Todo } from './models/todo.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TodoInput, TodoList],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  private nextId: number = 1;
  //todos: Todo[] = []

  todos = signal<Todo[]>([]);

  onAddTodo(title: string) {
    const newTodo: Todo = { id: this.nextId++, title, done: false };
    this.todos.update((list) => [...list, newTodo]);
    console.log(this.todos());

    //console.log("The todo received : " + todo)
  }

  onToggleTodo(id: number) {
    this.todos.update((list) =>
      list.map((todo) => (todo.id === id ? { ...todo, done: !todo.done } : todo))
    );
  }

  onDeleteTodo(id: number) {
    this.todos.update((list) => list.filter((todo) => todo.id !== id));
  }
}
