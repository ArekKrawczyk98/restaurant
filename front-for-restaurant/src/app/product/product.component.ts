import { Component, OnInit } from '@angular/core';
import {ProductService} from './product.service';
import {Product} from './product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
  }

  showAllProducts() {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data;
      for (const i of this.products) {
        console.log(i);
      }
    });
  }

}
