import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DirectiveDemo } from './directive/directive';

@Component({
  selector: 'app-root',
  imports: [DirectiveDemo],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('02-directive-demo');
}
