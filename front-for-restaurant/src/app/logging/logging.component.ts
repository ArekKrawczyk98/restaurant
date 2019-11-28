import { Component, OnInit } from '@angular/core';
import {Logging} from './logging';
import {LoggingService} from './logging.service';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {Router} from '@angular/router';


@Component({
  selector: 'app-logging',
  templateUrl: './logging.component.html',
  styleUrls: ['./logging.component.css']
})
export class LoggingComponent implements OnInit {

  logging: Logging;
  service: LoggingService;
  canPass: boolean;




  constructor(private httpService: HttpClient, private router: Router) {
    this.service = new LoggingService(httpService);
  }

  ngOnInit() {

  }

  async onSubmit(form: NgForm) {
    this.logging = form.value;
    this.service.postDetails(this.logging).subscribe((data) => {
      this.canPass = data.value;
      if (this.canPass) {
        // bad practice but its enough for this app, there is no need to send constant requests to backend for authentication
        localStorage.setItem('loggedIn', 'true');
        this.router.navigate(['/welcome']);

      } else {
        alert('cant pass');
      }

      console.log(this.canPass);
    } );


  }

}
