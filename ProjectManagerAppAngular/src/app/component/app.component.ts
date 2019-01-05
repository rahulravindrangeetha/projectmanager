import { Component } from '@angular/core';
import { ProjectManagerHyperLinkComponent } from './projectmanagerapp.hyperlink.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: '../html/app.component.html',
  styleUrls: ['../app.component.css']
})
export class ProjectManagerMainComponent 
{
  title = 'Project Manager';

  constructor(private router:Router)
  {
   
  }

  ngOnInit()
  {
    this.router.navigate(['/addproject']);
  }
  
}
