import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductComponent} from './product/product.component';
import {WelcomeComponent} from './welcome/welcome.component';
import {LoggingComponent} from './logging/logging.component';
import {BillsComponent} from './bills/bills.component';
import {SummaryComponent} from './summary/summary.component';


const routes: Routes = [
  { path: 'products', component: ProductComponent},
  { path: '', component: LoggingComponent},
  { path: 'welcome', component: WelcomeComponent},
  {path: 'bills', component: BillsComponent},
  { path: 'summary', component: SummaryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
