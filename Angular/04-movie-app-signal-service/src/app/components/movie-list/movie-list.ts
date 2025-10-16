import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-movie-list',
  imports: [CommonModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList {
  private movieService = inject(MovieService);

  movies = this.movieService.movies;

  onSelect(movieId: number) {
    const selectedMovie = this.movieService.selectMovie(movieId);
    if (selectedMovie) {
      alert(`Selected: ${selectedMovie.name} (${selectedMovie.yearOfRelease})`);
    }
  }

  onDelete(movieId: number) {
    if (confirm('Are you sure you want to delete this movie?')) {
      this.movieService.remove(movieId);
    }
  }
}
