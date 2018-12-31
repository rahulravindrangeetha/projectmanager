import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AddUserComponent } from './component/projectmanagerapp.adduser.component';
import { ProjectManagerMainComponent } from './component/app.component';
import { ProjectManagerHyperLinkComponent } from './component/projectmanagerapp.hyperlink.component';
import { Pipe, PipeTransform } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BsDatepickerModule} from '../../node_modules/ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap';
import { UserfilterPipe } from './userfilter.pipe';
import { CategoryfilterPipe } from './categoryfilter.pipe';
import { DatePipe } from '@angular/common';
import { OrderModule } from 'ngx-order-pipe';


@NgModule({
  declarations: [
    ProjectManagerMainComponent,
    ProjectManagerHyperLinkComponent,
    UserfilterPipe,
    AddUserComponent,
    CategoryfilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    FormsModule,
    OrderModule
  ],
  providers: [DatePipe],
  bootstrap: [ProjectManagerMainComponent]
})
export class AppModule { }
