import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Movie } from '../../models/movie.model';

@Component({
  selector: 'app-add-movie-form',
  imports: [FormsModule],
  templateUrl: './add-movie-form.html',
  styleUrl: './add-movie-form.css'
})
export class AddMovieForm {
  @Output() movieAdded = new EventEmitter<Movie>();

  movie: Partial<Movie> = {
    name: '',
    yearOfRelease: new Date().getFullYear(),
    actors: [],
    movieImgUrl: ''
  };

  actorsText: string = '';

  onSubmit() {
    if (this.movie.name && this.movie.yearOfRelease && this.actorsText && this.movie.movieImgUrl) {
      this.movie.actors = this.actorsText.split(',').map(actor => actor.trim()).filter(actor => actor.length > 0);
      this.movie.id = Date.now();
      this.movieAdded.emit(this.movie as Movie);
      
      this.resetForm();
    }
  }

  private resetForm() {
    this.movie = {
      name: '',
      yearOfRelease: new Date().getFullYear(),
      actors: [],
      movieImgUrl: ''
    };
    this.actorsText = '';
  }
}
