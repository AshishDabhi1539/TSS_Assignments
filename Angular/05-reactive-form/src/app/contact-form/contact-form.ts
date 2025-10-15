import { Component } from '@angular/core';
import { User } from '../models/user.model';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact-form',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './contact-form.html',
  styleUrl: './contact-form.css'
})
export class ContactForm {
educations: User['highestEducation'][]=['Diploma','Graduate','Post Graduate','Doctorate'];

userForm = new FormGroup({
  name:new FormControl<string>(''),
  email:new FormControl<string>(''),
  dob:new FormControl<string>(''),
  gender:new FormControl<User['gender']>('male'),
  highestEducation:new FormControl<User['highestEducation']>('Graduate')
})

onFormSubmit(){
  console.log(this.userForm.value);
  
}
}
