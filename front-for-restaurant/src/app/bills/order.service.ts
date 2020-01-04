import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
class OrderService {
  private urlToAddOrder = 'https://localhost:8080/orders';
  private urlToShowOrders = '';
  constructor(private service: HttpClient) {
  }

  getAllOrders() {
    return this.service.get(this.urlToShowOrders);
  }

  getOrderById(id: string) {
    const url = this.urlToAddOrder + '/id';
    return this.service.get(url);
  }



}
