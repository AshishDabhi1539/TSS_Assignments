export interface StudentResponse{
    id:number
    name:string
    email:string
    age:number | null
}

export interface StudentRequest{
    name:string
    email:string
    age:number | null
}

//Local Aliases
export type Student=StudentResponse;