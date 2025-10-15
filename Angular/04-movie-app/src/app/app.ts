import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Movie } from './models/movie.model';
import { AddMovieForm } from './components/add-movie-form/add-movie-form';
import { MovieList } from './components/movie-list/movie-list';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CommonModule, AddMovieForm, MovieList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Movie Collection App');
  
  //movies: Movie[] = [];
  movies = signal<Movie[]>([]);
  
  //selectedMovieId: number | null = null;
  selectedMovieId = signal<number | null>(null);

  onMovieAdded(movie: Movie) {
    //this.movies = [...this.movies, movie];
    this.movies.update((currentMovies) => [...currentMovies, movie]);
    console.log('Movie added:', movie);
  }

  onMovieSelected(movieId: number) {
    //this.selectedMovieId = movieId;
    this.selectedMovieId.set(movieId);
    
    //const selectedMovie = this.movies.find(m => m.id === movieId);
    const selectedMovie = this.movies().find(m => m.id === movieId);
    console.log('Movie selected:', selectedMovie);
    alert(`Selected: ${selectedMovie?.name} (${selectedMovie?.yearOfRelease})`);
  }

  onMovieDeleted(movieId: number) {
    //this.movies = this.movies.filter(movie => movie.id !== movieId);
    this.movies.update((currentMovies) => currentMovies.filter(movie => movie.id !== movieId));
    
    if (this.selectedMovieId() === movieId) {
      this.selectedMovieId.set(null);
    }
    console.log('Movie deleted:', movieId);
  }
}
