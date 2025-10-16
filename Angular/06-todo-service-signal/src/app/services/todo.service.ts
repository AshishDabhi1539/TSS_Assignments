// import { Injectable, signal } from "@angular/core";
// import { Todo } from "../models/todo.model";

// @Injectable({
//     providedIn:'root'
// })
// export class TodoService{
//     private _todos = signal<Todo[]>([] as Todo[]);

//     todos = this._todos.asReadonly();

//     private nextId = this.computeNextId(this._todos());

//     computeNextId(list: Todo[]){
//         return list.length ? Math.max(...list.map(t=>t.id))+1 : 1;
//     }

//     add(title:string){
//         const newTodo: Todo = {id: this.nextId++,title:title,done:false};
//         this._todos.update(list=>[...list,newTodo]);
//         console.log("Todo Service Todo[]: ",this._todos())
//     }

//     remove(id:number){
//         this._todos.update(list=>list.filter(t=> t.id !=id));
//     }

//     toggle(id:number){
//         this._todos.update(list=> list.map(t=>{
//             return t.id===id? {...t,done:!t.done}:t;
//         }))
//     }

//     clearAllTodos(){
//         this._todos.set([] as Todo[]);
//         this.nextId=1;
//     }
// }

import { computed, effect, Injectable, signal } from "@angular/core";
import { Todo } from "../models/todo.model";

const TODOS_KEY = "todos";

function loadTodos(): Todo[] {
  try {
    const rawData = localStorage.getItem(TODOS_KEY);
    if (!rawData) return [];
    const parsed = JSON.parse(rawData);

    if (Array.isArray(parsed)) {
      return parsed.map(t => ({ id: t.id, title: t.title, done: !!t.done })) as Todo[];
    }
    return [];
  } catch {
    return [];
  }
}

@Injectable({
  providedIn: 'root'
})
export class TodoService {

  private _todos = signal<Todo[]>(loadTodos());
  todos = this._todos.asReadonly();

  // Computed signals for counts
  pendingCount = computed(() => this._todos().filter(t => !t.done).length);
  doneCount = computed(() => this._todos().filter(t => t.done).length);

  constructor() {
    effect(() => {
      localStorage.setItem(TODOS_KEY, JSON.stringify(this._todos()));
    });
  }

  private nextId = this.computeNextId(this._todos());

  computeNextId(list: Todo[]) {
    return list.length ? Math.max(...list.map(t => t.id)) + 1 : 1;
  }

  add(title: string) {
    const newTodo: Todo = { id: this.nextId++, title: title, done: false };
    this._todos.update(list => [...list, newTodo]);
  }

  remove(id: number) {
    this._todos.update(list => list.filter(t => t.id !== id));
  }

  toggle(id: number) {
    this._todos.update(list => list.map(t => t.id === id ? { ...t, done: !t.done } : t));
  }

  clearAllTodos() {
    this._todos.set([] as Todo[]);
    this.nextId = 1;
  }
}