import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodoInput } from './components/todo-input/todo-input';
import { TodoList } from './components/todo-list/todo-list';
import { Todo } from './models/todo.model';

@Component({
  selector: 'app-root',
  standalone:true,
  imports: [TodoInput,TodoList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private nextId:number = 1;
  todos: Todo[] = []

  onAddTodo(todo:string){

    const newTodo:Todo = {id:this.nextId++, title:todo, done:false}
    this.todos = [...this.todos,newTodo]
    console.log(this.todos)
    
    //console.log("The todo received : " + todo)
  }

  onToggleTodo(id: number) {
    this.todos = this.todos.map(todo =>
      todo.id === id ? { ...todo, done: !todo.done } : todo
    );
  }

  onDeleteTodo(id: number) {
    this.todos = this.todos.filter(todo => todo.id !== id);
  }
}
