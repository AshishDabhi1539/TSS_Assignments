import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Student, StudentRequest } from "../models/model.student";
import { Observable } from "rxjs";

const API_URL = 'http://localhost:8080/api';

@Injectable({providedIn:"root"})
export class StudentService{
    private http = inject(HttpClient);
    private base_url = `${API_URL}/students`;

    list(): Observable<Student[]>{
        return this.http.get<Student[]>(this.base_url);
    }

    get(id:number): Observable<Student>{
        return this.http.get<Student>(`${this.base_url}/${id}`);
    }

    create(dto:StudentRequest):Observable<Student>{
        return this.http.post<Student>(this.base_url,dto);
    }

    update(id:number,dto:StudentRequest):Observable<Student>{
        return this.http.put<Student>(`${this.base_url}/${id}`,dto);
    }

    delete(id:number):Observable<void>{
        return this.http.delete<void>(`${this.base_url}/${id}`);
    }
}