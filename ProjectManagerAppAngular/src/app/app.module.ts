import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { WorkoutMainComponent } from './component/app.component';
import { WorkoutHyperLinkComponent } from './component/workoutapp.hyperlink.component';
import { WorkoutDisplayComponent } from './component/workoutapp.showworkouts.component';
import { WorkoutEditComponent } from './component/workoutapp.editworkout.component';
import { WorkoutStartComponent } from './component/workoutapp.startworkout.component';
import { WorkoutEndComponent } from './component/workoutapp.endworkout.component';
import { WorkoutAddComponent } from './component/workoutapp.addworkout.component';
import { WorkoutCategoryShowComponent } from './component/workoutapp.showcategory.component';
import { WorkoutCategoryEditComponent } from './component/workoutapp.editcategory.component';
import { WorkoutDeleteComponent } from './component/workoutapp.deleteworkout.component';
import { WorkoutCategoryCreateComponent } from './component/workoutapp.addcategory.component';
import { WorkoutCategoryDeleteComponent } from './component/workoutapp.deletecategory.component';
import { WorkoutReportComponent } from './component/workoutapp.showreport.component';
import { Pipe, PipeTransform } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BsDatepickerModule} from '../../node_modules/ngx-bootstrap/datepicker';
import { TimepickerModule } from 'ngx-bootstrap';
import { WorkoutfilterPipe } from './workoutfilter.pipe';
import { CategoryfilterPipe } from './categoryfilter.pipe';
import { AmazingTimePickerModule} from 'amazing-time-picker';
import { DatePipe } from '@angular/common';


@NgModule({
  declarations: [
    WorkoutMainComponent,
    WorkoutHyperLinkComponent,
    WorkoutDisplayComponent,
    WorkoutEditComponent,
    WorkoutDeleteComponent,
    WorkoutStartComponent,
    WorkoutEndComponent,
    WorkoutAddComponent,
    WorkoutCategoryShowComponent,
    WorkoutCategoryEditComponent,
    WorkoutCategoryCreateComponent,
    WorkoutCategoryDeleteComponent,
    WorkoutReportComponent,
    WorkoutfilterPipe,
    CategoryfilterPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BsDatepickerModule.forRoot(),
    TimepickerModule.forRoot(),
    FormsModule,
    AmazingTimePickerModule
  ],
  providers: [DatePipe],
  bootstrap: [WorkoutMainComponent]
})
export class AppModule { }
