export interface User{
    name:string,
    email:string,
    dob:string,
    gender:'male' | 'female',
    highestEducation:'Diploma'|'Graduate'|'Post Graduate'|'Doctorate',
    skills:string[],
    
}