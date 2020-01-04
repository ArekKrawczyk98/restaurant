import {Bill} from './bill';

export class BillToBePaid {
  bill: Bill;
  money: number;


  constructor(bill: Bill, money: number) {
    this.bill = bill;
    this.money = money;
  }
}
