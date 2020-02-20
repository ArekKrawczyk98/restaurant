import {Product} from '../product/product';


export class Order {
  id: number;
  products: Product[];

  addToOrder(product: Product) {
    this.products.push(product);
}

  constructor(id: number) {
    this.id = id;
    this.products = [];
  }
  totalCost(): number {
    let total = 0;
    this.products.forEach(value => {
      total += value.cost;
    });
    return total;
  }
}
