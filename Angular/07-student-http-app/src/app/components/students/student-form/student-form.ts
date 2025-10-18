import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { NotExpr } from '@angular/compiler';

@Component({
  selector: 'app-student-form',
  standalone:true,
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './student-form.html',
  styleUrl: './student-form.css'
})
export class StudentForm implements OnInit {
  
  private fb = inject(FormBuilder);
  private studentService = inject(StudentService);
  private route = inject(ActivatedRoute);

  isEdit = false;
  id:number | null = null;

  form = this.fb.group({
    name: new FormControl('',[Validators.required,Validators.minLength(3)]),
    email:['',[Validators.required,Validators.email]],
    age:[null,[Validators.required,Validators.min(15),Validators.max(70)]]
  });

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.isEdit = !!idParam && !isNaN(+idParam);

    if (this.isEdit) {
      this.id = Number(idParam);
      this.studentService.get(this.id!)
        .subscribe({
          next: s => {
            this.form.patchValue({
              name: s.name,
              email: s.email,
              age: s.age ?? null
            }as any);
          },
          error: err => {
            console.error('Error fetching student details:', err);
          }
        });
    }
  }
}
