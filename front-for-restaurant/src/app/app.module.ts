import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BillComponent } from './bill/bill.component';
import {BillService} from './bill/bill.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { LoggingComponent } from './logging/logging.component';
import {LoggingService} from './logging/logging.service';
import {RouterModule} from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { ProductComponent } from './product/product.component';
import {ProductService} from './product/product.service';
import {AppRoutingModule} from './app-routing-module';
import { HeaderComponent } from './header/header.component';


@NgModule({
  declarations: [
    AppComponent,
    BillComponent,
    LoggingComponent,
    WelcomeComponent,
    ProductComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule, HttpClientModule, FormsModule, RouterModule, AppRoutingModule,
  ],
  providers: [BillService, LoggingService, ProductService],
  bootstrap: [AppComponent]
})
export class AppModule { }
