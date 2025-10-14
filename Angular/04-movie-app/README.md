# Angular Movie Listing App

A small Angular application that demonstrates parent-child communication using `@Input()` and `@Output()` decorators.

## Features

- **Add Movie Form**: Collect movie details and emit new movie to parent component
- **Movie List**: Display movie collection with select and delete functionality
- **Parent-Child Communication**: Demonstrates proper data flow between components
- **Responsive Design**: Bootstrap-based UI with mobile-friendly layout
- **Movie Cards**: Beautiful card-based layout for each movie

## Project Structure

```
src/app/
├── models/
│   └── movie.model.ts          # Movie interface definition
├── components/
│   ├── add-movie-form/         # Form component for adding movies
│   └── movie-list/             # Component for displaying movie list
├── app.ts                      # Main app component (parent)
├── app.html                    # Main app template
└── app.css                     # Global styles
```

## Movie Data Model

Each movie has the following properties:
- `id`: number (auto-generated)
- `name`: string
- `yearOfRelease`: number
- `actors`: string[] (entered as comma-separated text)
- `movieImgUrl`: string

## Component Communication

### Parent Component (App)
- Manages the movie array state
- Handles movie addition, selection, and deletion
- Coordinates communication between child components

### Child Components

#### AddMovieFormComponent
- **@Output()** `movieAdded`: Emits new movie object to parent
- Form validation and data processing
- Resets form after successful submission

#### MovieListComponent
- **@Input()** `movies`: Receives movie array from parent
- **@Output()** `movieSelected`: Emits movie ID when selected
- **@Output()** `movieDeleted`: Emits movie ID for deletion

## How to Run

1. **Install dependencies**:
   ```bash
   npm install
   ```

2. **Start development server**:
   ```bash
   ng serve
   ```

3. **Open browser**:
   Navigate to `http://localhost:4200`

## Usage

1. **Add a Movie**:
   - Fill in the movie details in the form
   - Enter actors as comma-separated values
   - Provide a valid image URL
   - Click "Add Movie"

2. **View Movies**:
   - Movies are displayed as cards in the main area
   - Each card shows poster, name, year, and actors

3. **Select a Movie**:
   - Click the "Select" button on any movie card
   - Selected movie ID will be displayed at the bottom

4. **Delete a Movie**:
   - Click the "Delete" button on any movie card
   - Confirm deletion in the popup dialog

## Technologies Used

- **Angular 20**: Latest Angular framework
- **Bootstrap 5**: CSS framework for responsive design
- **TypeScript**: Type-safe JavaScript
- **Angular Forms**: Template-driven forms with validation

## Key Angular Concepts Demonstrated

- Component architecture
- Parent-child communication via @Input() and @Output()
- Template-driven forms
- Event handling
- Data binding (property and event binding)
- Structural directives (*ngIf, @for)
- Component lifecycle
- TypeScript interfaces