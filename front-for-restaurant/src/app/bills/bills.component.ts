import {Component, OnInit} from '@angular/core';
import {Bill} from './bill';
import {BillService} from './bill.service';
import {OrderService} from './order.service';
import {NgForm} from '@angular/forms';
import {Product} from '../product/product';
import {ProductService} from '../product/product.service';
import {Order} from './order';
import {BillToBePaid} from './billToBePaid';
import {AddOrderDTO} from '../DTO/AddOrderDTO';
import {BillSplitDTO} from "../DTO/BillSplitDTO";

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
  ordersForSelectedBill: Order[];
  billSelectedToAddOrder: Bill;
  billSelectedToBePaid: Bill;
  private showPayLabel: boolean;
  private moneyGiven: number;
  private orderCount = 0;
  sendOrderButtonClicked: boolean;
  private showRestLabel: boolean;
  private rest: number;
  productsToBeAddedToAnotherBill: Product[] = [];

  constructor(private service: BillService, private productService: ProductService, private orderService: OrderService) { }

  ngOnInit() {
  this.showAllBills();
  }
  addBill(form: NgForm) {
    this.addClicked = false;
    this.billToBeAdded = {
      date: new Date(), id: 0, toPay: 0, table: Number(form.value.number)
    };
    this.service.addBill(this.billToBeAdded).subscribe((data: Bill) => {
      this.billAdded = data;
      if (this.billAdded === null) {
      } else {
        this.bills.push(this.billAdded);
      }
    });
  }
  payBill(bill: Bill, money: number) {
    this.service.payBill( new BillToBePaid(bill, money)).subscribe((data: number) => {
      this.bills.splice(this.bills.indexOf(bill), 1);
      this.showPayLabel = ! this.showPayLabel;
      if (data !== 0) {
        this.rest = data;
        this.showRestLabel = true;
      }
    });

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
      this.orderCount++;
      this.order = new Order(this.orderCount);
      this.billSelectedToAddOrder = bill;
      this.sendOrderButtonClicked = false;
    });

  }

  choose(product: Product) {
    if (this.order === null) {
      this.orderCount++;
      this.order = new Order(this.orderCount);
    }
    this.order.addToOrder(product);
  }

  sendOrder(order: Order) {
    const orderToBeSent = new AddOrderDTO(order, this.billSelectedToAddOrder);
    this.orderService.postOrder(orderToBeSent).subscribe();
    this.billSelectedToAddOrder.toPay += order.totalCost();
    this.sendOrderButtonClicked = true;
    this.order = null;

  }

  showOrdersForThisBill(bill: Bill) {
    this.orderService.getAllOrdersByBillId(bill.id).subscribe((data: Order[]) => {
      if (data.length !== 0) {
        this.ordersForSelectedBill = data;
      } else {
        this.ordersForSelectedBill = null;
      }
    });
  }
  splitTheBill(bill: Bill){
    const billSplit= new BillSplitDTO(this.productsToBeAddedToAnotherBill, bill);
    this.service.splitTheBill(billSplit).subscribe();
    this.productsToBeAddedToAnotherBill = null;
    this.showAllBills();

}

  addProductToSplitList (orderId:number,product: Product ) {

      this.productsToBeAddedToAnotherBill.push(product);
      const order = this.ordersForSelectedBill[orderId];
      for(let i=0; i<order.products.length; i++){
        if (order.products[i]==product){
          order.products.splice(i,1);
        }
      }

  }
}
