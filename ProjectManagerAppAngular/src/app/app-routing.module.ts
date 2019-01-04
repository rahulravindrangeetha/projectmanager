import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectManagerMainComponent } from './component/app.component';
import { ProjectManagerHyperLinkComponent } from './component/projectmanagerapp.hyperlink.component';
import { AddUserComponent } from './component/projectmanagerapp.adduser.component';
import { AddProjectComponent } from './component/projectmanagerapp.addproject.component';
import { AddTaskComponent } from './component/projectmanagerapp.addtask.component';
import { ViewTaskComponent } from './component/projectmanagerapp.viewtask.component';

const routes: Routes = [
  {path:'adduser',component:AddUserComponent},
   {path:'addproject',component:AddProjectComponent},
   {path:'addtask',component:AddTaskComponent},
   {path:'viewtask',component:ViewTaskComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
