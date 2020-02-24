import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class SummaryService {

  private url = 'http://localhost:8080/invoice';

  constructor(private httpService: HttpClient) {}

  getInvoicesForCurrentDay() {
    return this.httpService.get(this.url);
  }

  endService() {
    return this.httpService.get('http://localhost:8080/end');
  }

  nowCanEndService() {
    this.httpService.get('http://localhost:8080/end=true');
  }
}
