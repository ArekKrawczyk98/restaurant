import {Component, OnInit} from '@angular/core';
import {Bill} from './bill';
import {BillService} from './bill.service';
import {NgForm} from '@angular/forms';
import {Product} from '../product/product';
import {ProductService} from '../product/product.service';
import {Order} from './order';
import {BillToBePaid} from './billToBePaid';

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
  products: Product[];
  order: Order;
  billSelectedToAddOrder: Bill;
  billSelectedToBePaid: Bill;
  private showPayLabel: boolean;
  private moneyGiven: number;

  constructor(private service: BillService, private productService: ProductService) { }

  ngOnInit() {
  this.showAllBills();
  }
  addBill(form: NgForm) {
    this.addClicked = false;
    this.billToBeAdded = {
      date: new Date(), id: this.bills.length, toPay: 0, table: Number(form.value.number)
    };
    this.service.addBill(this.billToBeAdded).subscribe((data: Bill) => {
      this.billAdded = data;
      if (this.billAdded === null) {
        alert('Cant add another bill to same table number');
      } else {
        this.bills.push(this.billAdded);
      }
    });
  }
  payBill(bill: Bill, money: number) {
    this.service.payBill( new BillToBePaid(bill, money)).subscribe();
  }

  selectBill(bill: Bill) {
    this.billSelectedToBePaid = bill;
    this.showPayLabel = ! this.showPayLabel;
  }

  deleteBill(bill: Bill) {
    this.service.delete(bill).subscribe();
    this.bills.splice(this.bills.indexOf(bill), 1);
  }
  showFormToAdd() {
    this.addClicked = !this.addClicked;
  }

  showAllBills() {
    this.service.getAllBills().subscribe((data: Bill[]) => {
      data.forEach((item, index) => {
        this.bills.push(item);
        this.bills[index].table = item.table;
      });
    });
  }

  showProducts(bill: Bill) {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data;
      this.order = new Order('sth');
      this.billSelectedToAddOrder = bill;
    });
  }

  choose(product: Product) {
    if (this.order === null) {
      this.order = new Order('id');
    }
    this.order.addToOrder(product);
  }

  sendOrder(order: Order) {
    // TODO sending order
    this.order = null;
    this.billSelectedToAddOrder = null;
  }

  showOrdersForThisBill(billId: number) {
     // TODO show orders
  }
}
