import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Logging} from './logging';
import {Observable} from 'rxjs';

@Injectable()
export class LoggingService {


  private url = 'http://localhost:8080/start';

  constructor(private httpService: HttpClient) {}

  postDetails(body: Logging): Observable<any> {
    return this.httpService.post(this.url, body);
  }



}
