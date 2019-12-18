import { Component, OnInit } from '@angular/core';
import {Bill} from './bill';
import {BillService} from './bill.service';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit {
  addClicked: boolean;
  bills: Bill[] = [];
  billToBeAdded: Bill;
  billAdded: Bill;

  constructor(private service: BillService) { }

  ngOnInit() {
    this.service.getAllBills().subscribe((data: Bill[]) => {
      this.bills = data;
    });
  }

  addBill() {
    this.addClicked = true;
    const bill: Bill = {
      date: undefined, id: 0, moneyPaid: 0, table: this.bills.length

    };
    bill.date = new Date();
    this.billToBeAdded = bill;
    this.service.addBill(this.billToBeAdded).subscribe((data: Bill) => {
      this.billAdded = data;
      this.bills.push(this.billAdded);
    });
  }


  payBill(bill: Bill) {
    this.service.payBill(bill).subscribe();
  }

  deleteBill(bill: Bill) {
    this.service.delete(bill).subscribe();

  }
}
