export interface Product {
  id: number;
  name: string;
  category: 'Laptop' | 'Phone' | 'Accessories';
  price: number;
  stock: number;
}

export const PRODUCTS: Product[] = [
  {
    id: 1,
    name: 'Asus Vivobook 15',
    category: 'Laptop',
    price: 59000,
    stock: 10,
  },
  {
    id: 2,
    name: 'HP Pavilion x360',
    category: 'Laptop',
    price: 79000,
    stock: 15,
  },
  {
    id: 3,
    name: 'Apple iPhone 15 Pro',
    category: 'Phone',
    price: 150000,
    stock: 8,
  },
  {
    id: 4,
    name: 'Samsung Galaxy S24',
    category: 'Phone',
    price: 95000,
    stock: 20,
  },
  {
    id: 5,
    name: 'Lenovo IdeaPad Slim 5',
    category: 'Laptop',
    price: 65000,
    stock: 12,
  },
  {
    id: 6,
    name: 'Boat Rockerz 550 Headphones',
    category: 'Accessories',
    price: 2500,
    stock: 50,
  },
  {
    id: 7,
    name: 'Logitech MX Master 3S Mouse',
    category: 'Accessories',
    price: 9500,
    stock: 0,
  }
];
