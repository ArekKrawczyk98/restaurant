import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillsComponent } from './bills.component';
import {Order} from './order';
import {Product} from '../product/product';

describe('BillsComponent', () => {
  let component: BillsComponent;
  let fixture: ComponentFixture<BillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get right prices', () => {
    const p1 = new Product(1, 'name1', 25.0, 'CATEGORY');
    const p2 = new Product(2, 'name2', 15.0, 'CATEGORY2');
    const p3 = new Product(3, 'name3', 20.0, 'CATEGORY3');
    const order1 = new Order(1);
    order1.addToOrder(p1);
    order1.addToOrder(p2);
    order1.addToOrder(p3);

    expect(60).toEqual(order1.totalCost());


  });
});
