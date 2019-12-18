import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {BillService} from './bills/bill.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { LoggingComponent } from './logging/logging.component';
import {LoggingService} from './logging/logging.service';
import {RouterModule} from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { ProductComponent } from './product/product.component';
import {ProductService} from './product/product.service';
import {AppRoutingModule} from './app-routing-module';
import { HeaderComponent } from './header/header.component';
import { BillsComponent } from './bills/bills.component';
import { SummaryComponent } from './summary/summary.component';


@NgModule({
  declarations: [
    AppComponent,
    LoggingComponent,
    WelcomeComponent,
    ProductComponent,
    HeaderComponent,
    BillsComponent,
    SummaryComponent,
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, RouterModule, AppRoutingModule, ReactiveFormsModule
  ],
  providers: [BillService, LoggingService, ProductService],
  bootstrap: [AppComponent],
})
export class AppModule { }
