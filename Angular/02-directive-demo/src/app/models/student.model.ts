export interface Student {
  id: number;
  name: string;
  grade: 'A' | 'B' | 'C';
  email: string;
  attendance: number;
}

export const STUDENTS: Student[] = [
  {
    id: 1,
    name: 'Ashish Dabhi',
    grade: 'A',
    email: 'ashish.dabhi@gmail.com',
    attendance: 95,
  },
  {
    id: 2,
    name: 'Deep Ratanpara',
    grade: 'B',
    email: 'deep.ratanpara@gmail.com',
    attendance: 88,
  },
  {
    id: 3,
    name: 'Rohit Sharma',
    grade: 'A',
    email: 'rohit.sharma@gmail.com',
    attendance: 97,
  },
  {
    id: 4,
    name: 'Priya Mehara',
    grade: 'C',
    email: 'priya.mehara@gmail.com',
    attendance: 76,
  },
  {
    id: 5,
    name: 'Sumit Makavana',
    grade: 'B',
    email: 'sumit.makavana@gmail.com',
    attendance: 82,
  },
  {
    id: 6,
    name: 'Dev Desai',
    grade: 'A',
    email: 'dev.desai@gmail.com',
    attendance: 91,
  },
  {
    id: 7,
    name: 'Aditya Parmar',
    grade: 'B',
    email: 'aditya.parmar@gmail.com',
    attendance: 85,
  },
  {
    id: 8,
    name: 'Kiran Sastri',
    grade: 'A',
    email: 'kiran.sastri@gmail.com',
    attendance: 94,
  },
  {
    id: 9,
    name: 'Manav Modi',
    grade: 'C',
    email: 'manav.modi@gmail.com',
    attendance: 72,
  },
  {
    id: 10,
    name: 'Diya Patel',
    grade: 'B',
    email: 'diya.patel@gmail.com',
    attendance: 89,
  }
];
