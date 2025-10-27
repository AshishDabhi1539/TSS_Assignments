import { CommonModule } from "@angular/common";
import { Component, inject, OnInit } from "@angular/core";
import { StudentService } from "../../../services/student.service";
import { ActivatedRoute } from "@angular/router";
import { Student } from "../../../models/model.student";
import { catchError, EMPTY, finalize } from "rxjs";

@Component({
  selector: 'app-student-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-details.html',
  styleUrl: './student-details.css'
})
export class StudentDetails implements OnInit {
  private studentService = inject(StudentService);
  private route = inject(ActivatedRoute);

  student?: Student;
  error?: string;
  loading = false;

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (!idParam) {
      this.error = 'Invalid student ID';
      return;
    }

    const id = Number(idParam);
    if (isNaN(id)) {
      this.error = 'Invalid student ID';
      return;
    }

    this.loading = true;
    this.studentService.get(id).pipe(
      catchError(err => {
        console.error(err);
        this.error = 'Failed to load student details';
        return EMPTY;
      }),
      finalize(() => this.loading = false)
    ).subscribe(student => {
      this.student = student;
    });
  }
}