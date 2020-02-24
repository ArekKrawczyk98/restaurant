export class Product {
  id: number;
  name: string;
  cost: number;
  category: string;


  constructor(id: number, name: string, cost: number, category: string) {
    this.id = id;
    this.name = name;
    this.cost = cost;
    this.category = category;
  }
}
