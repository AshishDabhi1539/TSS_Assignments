import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../models/model.student';
import { catchError, EMPTY, finalize, Subscription } from 'rxjs';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './student-list.html',
  styleUrl: './student-list.css',
})
export class StudentList implements OnInit {
  private studentService = inject(StudentService);
  students: Student[] = [];
  loading = false;
  error?: string;

  private listSub?: Subscription;
  private deleteSub?: Subscription;

  ngOnInit(): void {
    this.refresh();
  }

  refresh() {
    this.loading = true;
    this.error = undefined;

    this.listSub?.unsubscribe();

    this.listSub = this.studentService
      .list()
      .pipe(
        catchError((err) => {
          console.log(err);
          this.error = 'unable to Load Students';
          this.students = [];
          return EMPTY;
        }),
        finalize(() => {
          this.loading = false;
        })
      )
      .subscribe({
        next: (data) => {
          this.students = data;
        },
      });
  }
  onDelete(id: number) {
    if (!confirm('Are you sure you want to delete this Student')) return;

    this.deleteSub?.unsubscribe();
    this.deleteSub = this.studentService
      .delete(id)
      .pipe(
        finalize(() => {
          console.log('Finalize for delete student executed');
        })
      )
      .subscribe({
        next: () => this.refresh(),
        error: (err) => {
          console.log(err);
          console.log('Failed to delete the Student');
        },
      });
  }
}
