import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContactsForm } from './contacts-form/contacts-form';

@Component({
  selector: 'app-root',
  imports: [ContactsForm],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
