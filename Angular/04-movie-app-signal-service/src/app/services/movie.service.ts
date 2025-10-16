import { Injectable, signal } from "@angular/core";
import { Movie } from "../models/movie.model";

@Injectable({
    providedIn: 'root'
})
export class MovieService {
    private _movies = signal<Movie[]>([] as Movie[]);
    private _selectedMovieId = signal<number | null>(null);

    movies = this._movies.asReadonly();
    selectedMovieId = this._selectedMovieId.asReadonly();

    private nextId = this.computeNextId(this._movies());

    computeNextId(list: Movie[]) {
        return list.length ? Math.max(...list.map(m => m.id)) + 1 : 1;
    }

    add(movie: Omit<Movie, 'id'>) {
        const newMovie: Movie = { id: this.nextId, ...movie };
        this._movies.update(list => [...list, newMovie]);
        this.nextId = this.computeNextId(this._movies());
        console.log("Movie service Movies[]", this._movies());
    }

    remove(id: number) {
        this._movies.update(list => list.filter(m => m.id !== id));
        if (this._selectedMovieId() === id) {
            this._selectedMovieId.set(null);
        }
    }

    selectMovie(id: number) {
        this._selectedMovieId.set(id);
        const selectedMovie = this._movies().find(m => m.id === id);
        console.log('Movie selected:', selectedMovie);
        return selectedMovie;
    }

    clearSelection() {
        this._selectedMovieId.set(null);
    }

    getSelectedMovie() {
        const selectedId = this._selectedMovieId();
        return selectedId ? this._movies().find(m => m.id === selectedId) : null;
    }

    clearAllMovies() {
        this._movies.set([] as Movie[]);
        this._selectedMovieId.set(null);
        this.nextId = 1;
    }
}
