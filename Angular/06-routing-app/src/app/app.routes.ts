import { Routes } from '@angular/router';
import { UserHome } from './home/user-home/user-home';
import { ContactList } from './contact/contact-list/contact-list';
import { AdminLayout } from './admin/admin-layout/admin-layout';
import { NotFound } from './shared/not-found/not-found';

export const routes: Routes = [
  //   { path: '', redirectTo: 'home', pathMatch: 'full' },
  //   { path: 'home', component: UserHome, title: 'User Home' },
  //   { path: 'contacts', component: ContactList, title: 'Contact List' },
  //   { path: 'admin', component: AdminLayout, title: 'Admin Layout' },
  //   { path: '**', component: NotFound, title: 'Not Found' },
  {
    path: '',
    loadChildren:()=>import('./home/home.routes').then(m=>m.HOME_ROUTES),
    title: 'Home'
  },
  {
    path: 'contacts',
    loadChildren:()=>import('./contact/contact.routes').then(m=>m.CONTACTS_ROUTES),
    title: 'Contacts',
  },
  {
    path: 'admin',
    loadChildren:()=>import('./admin/admin.routes').then(m=>m.ADMIN_ROUTES),
    title: 'Admins',
  },
  {
    path: '**',
    component:NotFound,
    title: 'Not-Found',
  },
];
