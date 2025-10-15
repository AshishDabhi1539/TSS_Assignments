import { Component, Input, Output, EventEmitter, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-list',
  imports: [CommonModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList {
  //@Input() movies: Movie[] = [];
  movies = input.required<Movie[]>();

  //@Output() movieSelected = new EventEmitter<number>();
  //@Output() movieDeleted = new EventEmitter<number>();
  movieSelected = output<number>();
  movieDeleted = output<number>();

  onSelect(movieId: number) {
    this.movieSelected.emit(movieId);
  }

  onDelete(movieId: number) {
    if (confirm('Are you sure you want to delete this movie?')) {
      this.movieDeleted.emit(movieId);
    }
  }
}
