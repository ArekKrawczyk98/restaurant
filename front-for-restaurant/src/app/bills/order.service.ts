import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AddOrderDTO} from '../DTO/AddOrderDTO';

@Injectable()
export class OrderService {
  private urlForOrders = 'http://localhost:8080/orders';
  private urlToShowOrders = '';
  constructor(private service: HttpClient) {
  }

  postOrder(order: AddOrderDTO) {
    return this.service.post(this.urlForOrders, order);
  }

  getAllOrders() {
    return this.service.get(this.urlToShowOrders);
  }

  getOrderById(id: string) {
    const url = this.urlForOrders + '/id';
    return this.service.get(url);
  }


  getAllOrdersByBillId(billId: number) {
    const url = this.urlForOrders + '/billId=' + billId;
    return this.service.get(url);

  }
}
