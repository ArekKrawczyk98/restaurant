import {Order} from '../bills/order';
import {Bill} from '../bills/bill';

export class AddOrderDTO {
  order: Order;
  bill: Bill;

  constructor(order: Order, bill: Bill) {
    this.order = order;
    this.bill = bill;
  }
}
