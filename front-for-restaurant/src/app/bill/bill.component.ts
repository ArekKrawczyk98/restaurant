import { Component, OnInit } from '@angular/core';
import {BillService} from './bill.service';
import {Bill} from './bill';



@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {
  bill: Bill;

  ngOnInit(): void {
  }

  constructor(private billService: BillService) {
    billService.getBillById().subscribe((data)  => this.bill = data);
  }



}
