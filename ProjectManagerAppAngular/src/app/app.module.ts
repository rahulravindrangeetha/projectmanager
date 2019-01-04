import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AddUserComponent } from './component/projectmanagerapp.adduser.component';
import { AddTaskComponent } from './component/projectmanagerapp.addtask.component';
import { ViewTaskComponent } from './component/projectmanagerapp.viewtask.component';
import { AddProjectComponent } from './component/projectmanagerapp.addproject.component';
import { ProjectManagerMainComponent } from './component/app.component';
import { ProjectManagerHyperLinkComponent } from './component/projectmanagerapp.hyperlink.component';
import { Pipe, PipeTransform } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BsDatepickerModule} from '../../node_modules/ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap';
import { UserfilterPipe } from './userfilter.pipe';
import { ProjectfilterPipe } from './projectfilter.pipe';
import { ParentTaskfilterPipe } from './parenttaskfilter.pipe';
import { DatePipe } from '@angular/common';
import { OrderModule } from 'ngx-order-pipe';
import { Ng5SliderModule } from 'ng5-slider';


@NgModule({
  declarations: [
    ProjectManagerMainComponent,
    ProjectManagerHyperLinkComponent,
    UserfilterPipe,
    AddUserComponent,
    ProjectfilterPipe,
    AddProjectComponent,
    AddTaskComponent,
    ParentTaskfilterPipe,
    ViewTaskComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    FormsModule,
    OrderModule,
    Ng5SliderModule
  ],
  providers: [DatePipe],
  bootstrap: [ProjectManagerMainComponent]
})
export class AppModule { }
