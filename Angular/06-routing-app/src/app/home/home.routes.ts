import { Routes } from '@angular/router';
import { UserHome } from './user-home/user-home';

export const HOME_ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: UserHome,
    title: 'User-Home',
  }
];
