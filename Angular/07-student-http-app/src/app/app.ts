import { Component, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { NavBar } from './components/shared/nav-bar/nav-bar';

@Component({
  selector: 'app-root',
  imports: [NavBar,RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('07-student-http-app');
}
