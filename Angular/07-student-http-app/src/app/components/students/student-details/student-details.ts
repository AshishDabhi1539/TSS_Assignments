import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../models/model.student';
import { catchError, EMPTY, finalize, Subscribable, Subscription } from 'rxjs';

@Component({
  selector: 'app-student-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-details.html',
  styleUrl: './student-details.css',
})
export class StudentDetails{
  
}
