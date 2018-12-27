import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WorkoutMainComponent } from './component/app.component';
import { WorkoutHyperLinkComponent } from './component/workoutapp.hyperlink.component';
import { WorkoutDisplayComponent } from './component/workoutapp.showworkouts.component';
import { WorkoutEditComponent } from './component/workoutapp.editworkout.component';
import { WorkoutDeleteComponent } from './component/workoutapp.deleteworkout.component';
import { WorkoutStartComponent } from './component/workoutapp.startworkout.component';
import { WorkoutEndComponent } from './component/workoutapp.endworkout.component';
import { WorkoutAddComponent } from './component/workoutapp.addworkout.component';
import { WorkoutCategoryShowComponent } from './component/workoutapp.showcategory.component';
import { WorkoutCategoryEditComponent } from './component/workoutapp.editcategory.component';
import { WorkoutCategoryDeleteComponent } from './component/workoutapp.deletecategory.component';
import { WorkoutCategoryCreateComponent } from './component/workoutapp.addcategory.component';
import {WorkoutReportComponent} from './component/workoutapp.showreport.component';

const routes: Routes = [
  {path:'viewall',component:WorkoutDisplayComponent},
  {path:'create',component:WorkoutAddComponent},
  {path:'category',component:WorkoutCategoryShowComponent},
  {path:'',component:WorkoutDisplayComponent},
  {path:'track',component:WorkoutReportComponent},
  {path:'workout/edit/:workoutId',component:WorkoutEditComponent},
  {path:'workout/delete/:workoutId',component:WorkoutDeleteComponent},
  {path:'workout/start/:workoutId/:workoutTitle',component:WorkoutStartComponent},
  {path:'workout/end/:workoutId/:workoutTitle',component:WorkoutEndComponent},
  {path:'workoutCategory/edit/:categoryId/:categoryName',component:WorkoutCategoryEditComponent},
  {path:'workoutCategory/create/:categoryName',component:WorkoutCategoryCreateComponent},
  {path:'workoutCategory/delete/:categoryId',component:WorkoutCategoryDeleteComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
