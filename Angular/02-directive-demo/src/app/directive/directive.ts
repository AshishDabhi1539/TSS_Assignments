import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product, PRODUCTS } from '../models/products.model';
import { Student, STUDENTS } from '../models/student.model';

@Component({
  selector: 'app-directive',
  imports: [CommonModule],
  templateUrl: './directive.html',
  styleUrls: ['./directive.css']  // corrected
})
export class DirectiveDemo {
  products: Product[] = PRODUCTS;
  students: Student[] = STUDENTS;

  get grandTotal(): number {
    return this.products.reduce((sum, p) => sum + p.stock * p.price, 0);
  }
}
