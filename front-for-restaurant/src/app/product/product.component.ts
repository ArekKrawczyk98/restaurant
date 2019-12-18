import { Component, OnInit } from '@angular/core';
import {ProductService} from './product.service';
import {Product} from './product';
import {NgForm} from '@angular/forms';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Product[];
  productForUpdate: Product;
  productUpdated: any;
  updateClicked: boolean;
  addClicked: boolean;
  productAdded: Product;
  productToBeAdded: Product;

  constructor(private productService: ProductService) { }

  ngOnInit() {
  }

  showAllProducts() {
    this.productService.getAllProducts().subscribe((data: Product[]) => {
      this.products = data; });
  }

  updateCost(form: NgForm) {
    this.productForUpdate.cost = form.value.updatedCost;
    this.productService.updateProduct(this.productForUpdate).subscribe((data: Product) => {
      this.productUpdated = data;
    }
    );
    this.updateClicked = false;
  }

  showAddForm() {
   this.addClicked = !this.addClicked;
  }

  showUpdateLabel(product: Product) {
    this.updateClicked = true;
    this.productForUpdate = product;
  }

  addProduct(form: NgForm) {
    this.productToBeAdded = form.value;
    this.productService.addProduct(this.productToBeAdded).subscribe((data: Product) => {
       this.productAdded = data;
       this.addClicked = false;
       this.products.push(this.productAdded);
    });

  }

  deleteProduct(name: string) {
  this.productService.deleteProduct(name).subscribe((data) => {
    this.products.pop();
  });
  }
}
