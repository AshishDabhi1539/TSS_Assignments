import { Routes } from '@angular/router';
import { ContactList } from './contact-list/contact-list';
import { ContactDetails } from './contact-details/contact-details';

export const CONTACTS_ROUTES: Routes = [
  {
    path: '',
    component: ContactList,
    title: 'Contact-List',
  },
  {
    path: 'id',
    component: ContactDetails,
    title: 'Contact-Details',
  },
];
