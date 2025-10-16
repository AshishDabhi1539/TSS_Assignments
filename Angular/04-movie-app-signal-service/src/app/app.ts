import { Component, signal, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AddMovieForm } from './components/add-movie-form/add-movie-form';
import { MovieList } from './components/movie-list/movie-list';
import { MovieService } from './services/movie.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, AddMovieForm, MovieList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Movie Collection App with Service');
  
  // Inject the movie service
  private movieService = inject(MovieService);
  
  // Expose service signals for template usage if needed
  movies = this.movieService.movies;
  selectedMovieId = this.movieService.selectedMovieId;
}
