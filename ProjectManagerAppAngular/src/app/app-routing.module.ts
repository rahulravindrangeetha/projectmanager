import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectManagerMainComponent } from './component/app.component';
import { ProjectManagerHyperLinkComponent } from './component/projectmanagerapp.hyperlink.component';
import { AddUserComponent } from './component/projectmanagerapp.adduser.component';
import { AddProjectComponent } from './component/projectmanagerapp.addproject.component';

const routes: Routes = [
  {path:'adduser',component:AddUserComponent},
   {path:'addproject',component:AddProjectComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
