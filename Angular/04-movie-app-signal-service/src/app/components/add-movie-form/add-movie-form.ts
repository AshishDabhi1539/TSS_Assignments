import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Movie } from '../../models/movie.model';
import { MovieService } from '../../services/movie.service';

@Component({
  selector: 'app-add-movie-form',
  imports: [FormsModule],
  templateUrl: './add-movie-form.html',
  styleUrl: './add-movie-form.css'
})
export class AddMovieForm {
  // Inject the movie service
  private movieService = inject(MovieService);

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
      
      // Use the service to add the movie instead of emitting an event
      this.movieService.add(this.movie as Omit<Movie, 'id'>);
      
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
