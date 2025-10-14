import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-movie-list',
  imports: [CommonModule],
  templateUrl: './movie-list.html',
  styleUrl: './movie-list.css'
})
export class MovieList {
  @Input() movies: Movie[] = [];
  @Output() movieSelected = new EventEmitter<number>();
  @Output() movieDeleted = new EventEmitter<number>();

  onSelect(movieId: number) {
    this.movieSelected.emit(movieId);
  }

  onDelete(movieId: number) {
    if (confirm('Are you sure you want to delete this movie?')) {
      this.movieDeleted.emit(movieId);
    }
  }
}
