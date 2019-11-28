import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {Bill} from './bill';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class BillService {

  private url = 'http://localhost:8080/bills/all';

  constructor(private httpService: HttpClient) {}

  getAllBills(): Observable<Bill[]> {
    return this.httpService.get<Bill[]>(this.url);
  }
  getBillById(): Observable<Bill> {
    return this.httpService.get<Bill>('http://localhost:8080/bills/1');
  }

}
