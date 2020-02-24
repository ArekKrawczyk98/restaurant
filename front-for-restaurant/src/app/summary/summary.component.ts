import {ApplicationInitStatus, Component, OnInit} from '@angular/core';
import {SummaryService} from './summary.service';
import {Invoice} from './invoice';
import {AppComponent} from '../app.component';
import {AppModule} from '../app.module';

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

  endService() {
    this.summaryService.endService().subscribe((data: boolean) => {
      if (data) {
        this.summaryService.nowCanEndService();
      } else {
        console.log('cannot');
      }
    });
  }
}
