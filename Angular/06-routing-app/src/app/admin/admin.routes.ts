import { Routes } from '@angular/router';
import { AdminLayout } from './admin-layout/admin-layout';
import { AdminSettings } from './admin-settings/admin-settings';
import { AdminUsers } from './admin-users/admin-users';

export const ADMIN_ROUTES: Routes = [
  {
    path: '',
    component: AdminLayout,
    children: [
      {
        path: '',
        redirectTo: 'users',
        pathMatch: 'full',
      },
      {
        path: 'settings',
        component:AdminSettings,
        title:'Admin-Settings'
      },
      {
        path: 'users',
        component:AdminUsers,
        title:'Admin-User'
      }
    ],
  },
];
