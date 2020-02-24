import { Component, OnInit } from '@angular/core';
import {SummaryService} from './summary.service';
import {Invoice} from './invoice';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit {

  private invoices: Invoice[];

  constructor(private summaryService: SummaryService) { }

  ngOnInit() {
    this.showAllInvoicesForCurrentDay();
  }

  showAllInvoicesForCurrentDay() {
    this.summaryService.getInvoicesForCurrentDay().subscribe((data: Invoice[]) => {
      this.invoices = data;
    });
  }

}
