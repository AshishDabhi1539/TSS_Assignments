import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ContactModelForm } from '../models/contacts-form.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contacts-form',
  imports: [FormsModule,CommonModule],
  templateUrl: './contacts-form.html',
  styleUrl: './contacts-form.css',
})
export class ContactsForm {
  contactModel: ContactModelForm = {
    name: '',
    email: '',
    age: 0,
    message: '',
    agree:false
  };

  onFormSubmit(f: NgForm) {
    console.log('Form Values : ', f.value);
    console.log('ContactModel Values : ',this.contactModel);
    console.log('Form Valid : ', f.valid);
    console.table(this.contactModel);
    f.reset();
  }
}

