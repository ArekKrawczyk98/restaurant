import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Product} from './product';


@Injectable()
export class ProductService {

  private urlForAll = 'http://localhost:8080/products/all';
  private urlForUpdate = 'http://localhost:8080/products/update';
  private urlForMain = 'http://localhost:8080/products';
  private urlForDelete = 'http://localhost:8080/products/delete';

  constructor(private httpService: HttpClient) {}

  getAllProducts() {
    return this.httpService.get(this.urlForAll);
}
  updateProduct(product: Product) {
    return this.httpService.put(this.urlForUpdate, product);
  }


  addProduct(product: Product) {
    return this.httpService.post(this.urlForMain, product);

  }

  deleteProduct(nameOfString: string) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        name: nameOfString,
      },
    };
    return this.httpService.delete(this.urlForDelete, options);
  }
}
