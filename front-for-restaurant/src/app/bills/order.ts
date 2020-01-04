import {Product} from '../product/product';


export class Order {
  id: string;
  products: Product[];

  addToOrder(product: Product) {
    this.products.push(product);
}

  constructor(id: string) {
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
