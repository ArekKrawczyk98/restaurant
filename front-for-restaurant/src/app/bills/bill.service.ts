import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {Bill} from './bill';
import {BillToBePaid} from './billToBePaid';
import {BillSplitDTO} from "../DTO/BillSplitDTO";

@Injectable()
export class BillService {

  private url = 'http://localhost:8080/bills';
  private urlToPay = 'http://localhost:8080/bills/payBill';

  constructor(private httpService: HttpClient) {}

  addBill(bill: Bill) {
   return this.httpService.post(this.url, bill);
  }

  payBill(bill: BillToBePaid) {
    return this.httpService.post(this.urlToPay, bill);
  }

  delete(bill: Bill) {
    const urlForDelete = this.url + '/' +  bill.id;

    return this.httpService.delete(urlForDelete);
  }

  getAllBills() {
    const urlForAll = this.url + '/all';
    return this.httpService.get(urlForAll);
  }

  splitTheBill(billSplit: BillSplitDTO) {
    const urlForBillSplit = this.url+'/split';
    return this.httpService.post(urlForBillSplit,billSplit);

  }
}

