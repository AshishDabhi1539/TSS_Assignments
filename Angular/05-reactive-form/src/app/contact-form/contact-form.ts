import { Component } from '@angular/core';
import { User } from '../models/user.model';
import {
  AbstractControl,
  FormArray,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { parseISO } from 'date-fns';
import { retry } from 'rxjs';

function noDigits(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const v = (control.value ?? '') as string;
    return /\d/.test(v) ? { noDigits: true } : null;
  };
}
function minAge(minYears: number): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const v = control.value;
    if (!v) return null; // Skip if no value entered

    const dob = parseISO(v);
    if (isNaN(dob.getTime())) return { invalidDate: true }; // Invalid date format

    const today = new Date();
    const age = today.getFullYear() - dob.getFullYear();
    const hasBirthdayPassed =
      today.getMonth() > dob.getMonth() ||
      (today.getMonth() === dob.getMonth() && today.getDate() >= dob.getDate());

    const actualAge = hasBirthdayPassed ? age : age - 1;

    return actualAge < minYears ? { minAge: { requiredAge: minYears, actualAge } } : null;
  };
}

@Component({
  selector: 'app-contact-form',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './contact-form.html',
  styleUrl: './contact-form.css',
})
export class ContactForm {
  educations: User['highestEducation'][] = ['Diploma', 'Graduate', 'Post Graduate', 'Doctorate'];

  userForm = new FormGroup({
    name: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required, Validators.minLength(3), noDigits()],
    }),
    email: new FormControl<string>('', {
      nonNullable: true,
      validators: [
        Validators.required,
        Validators.email,
        Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
      ],
    }),
    dob: new FormControl<string>('', {
      nonNullable: true,
      validators: [Validators.required, minAge(18)],
    }),
    gender: new FormControl<User['gender']>('male'),
    highestEducation: new FormControl<User['highestEducation']>('Graduate'),

    skills: new FormArray<FormControl<string>>([
      new FormControl<string>('', {
        nonNullable: true,
        validators: [Validators.required, Validators.minLength(5)],
      }),
    ]),
  });

  get skills(): FormArray<FormControl<string>> {
    return this.userForm.get('skills') as FormArray<FormControl<string>>;
  }

  addSkill(): void {
    this.skills.push(
      new FormControl<string>('', {
        nonNullable: true,
        validators: [Validators.required, Validators.minLength(3)],
      })
    );
  }

  removeSkill(index: number): void {
    if (this.skills.length > 1) {
      this.skills.removeAt(index);
    } else {
      alert('At least one skill is required!');
    }
  }

  onFormSubmit() {
    console.log(this.userForm.value);
  }
}
