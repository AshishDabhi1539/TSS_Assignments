import { Routes } from '@angular/router';
import { StudentList } from './components/students/student-list/student-list';
import { StudentForm } from './components/students/student-form/student-form';
import { StudentDetails } from './components/students/student-details/student-details';
import { NotFound } from './components/shared/not-found/not-found';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'students',
    pathMatch: 'full',
  },
  // {
  //     path:'students',
  //     loadComponent:()=>import('./components/students/student-list/student-list').then(m=>m.StudentList),
  //     title:'Student List'
  // },
  {
    path: 'students',
    component: StudentList,
    title: 'Student List',
  },
  {
    path: 'students/add',
    component: StudentForm,
    title: 'Student Form',
  },
  {
    path: 'students/:id',
    component: StudentDetails,
    title: 'Student Details',
  },
  {
    path: 'students/:id/edit',
    component: StudentForm,
    title: 'Student Form Edit',
  },
  {
    path: '**',
    component: NotFound,
    title: 'Not Found',
  },
];
