import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { StudentRequest } from '../../../models/model.student';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './student-form.html',
  styleUrls: ['./student-form.css'] // fixed typo from styleUrl to styleUrls
})
export class StudentForm {

  private fb = inject(FormBuilder);
  private studentService = inject(StudentService);
  private route = inject(ActivatedRoute);
  private router = inject(Router); // ✅ inject Router

  isEdit = false;
  id: number | null = null;

  studentForm = this.fb.group({
    name: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.email]],
    age: [null as number | null, [Validators.required, Validators.min(10), Validators.max(100)]]
  });

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.isEdit = !!idParam && !isNaN(+idParam);

    if (this.isEdit) {
      this.id = Number(idParam);
      this.studentService.get(this.id!)
        .subscribe({
          next: s => {
            this.studentForm.patchValue({ name: s.name, email: s.email, age: s.age ?? null });
          }
        });
    }
  }

  onFormSubmit() {
    if (this.studentForm.invalid) {
      this.studentForm.markAllAsTouched();
      return;
    }

    const formValue = this.studentForm.value;
    const studentData: StudentRequest = {
      name: formValue.name!,
      email: formValue.email!,
      age: formValue.age!,
    };

    if (this.isEdit && this.id) {
      this.studentService.update(this.id, studentData).subscribe({
        next: () => {
          this.router.navigate(['/students']); 
        },
        error: err => console.error(err)
      });
    } else {
      this.studentService.create(studentData).subscribe({
        next: () => {
          this.router.navigate(['/students']); 
        },
        error: err => console.error(err)
      });
    }
  }
}
