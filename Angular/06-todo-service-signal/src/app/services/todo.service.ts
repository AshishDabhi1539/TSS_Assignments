import { Injectable, linkedSignal, signal } from "@angular/core";
import { Todo } from "../models/todo.model";

@Injectable({
    providedIn:'root'
})
export class TodoService{
    private _todos = signal<Todo[]>([] as Todo[]);

    todos = this._todos.asReadonly();

    private nextId = this.computeNextId(this._todos());

    computeNextId(list: Todo[]){
        return list.length ? Math.max(...list.map(t=>t.id))+1 : 1;
    }

    add(title:string){
        const newTodo: Todo = {id: this.nextId,title:title,done:false};
        this._todos.update(list=>[...list,newTodo]);
        console.log("Todo service Todo[]",this._todos());
        
    }

    remove(id:number){
        this._todos.update(list=>list.filter(t=> t.id !=id));
    }

    toggle(id:number){
        this._todos.update(list=> list.map(t=>{
            return t.id===id? {...t,done:!t.done}:t;
        }))
    }

    clearAllTodos(){
        this._todos.set([] as Todo[]);
        this.nextId=1;
    }
}