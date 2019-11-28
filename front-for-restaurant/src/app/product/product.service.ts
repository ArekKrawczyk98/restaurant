import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable()
export class ProductService {

  private urlForAll = 'http://localhost:8080/products/all';

  constructor(private httpService: HttpClient) {}

  getAllProducts() {
    return this.httpService.get(this.urlForAll);
}



}
