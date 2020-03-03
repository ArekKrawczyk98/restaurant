import {Product} from "../product/product";
import {Bill} from "../bills/bill";

export class BillSplitDTO {
  products: Product[];
  bill: Bill;

  constructor(products: Product[], bill: Bill) {
    this.products = products;
    this.bill = bill;
  }
}
