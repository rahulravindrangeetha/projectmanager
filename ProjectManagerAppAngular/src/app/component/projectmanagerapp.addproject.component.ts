import { Component } from '@angular/core';
import { ProjectManagerHyperLinkComponent } from './projectmanagerapp.hyperlink.component';
import { ProjectService} from '../service/projectservice'
import { ProjectfilterPipe } from '../projectfilter.pipe';
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { OrderModule } from 'ngx-order-pipe';
import { Project } from '../model/project.model';
import { BsDatepickerConfig } from '../../../node_modules/ngx-bootstrap/datepicker';
import { Options } from 'ng5-slider';


@Component({
  selector: 'projectmanagerapp-displayprojects',
  templateUrl: '../html/projectmanagerapp.addproject.component.html',
  styleUrls: ['../app.component.css'],
  providers: [ProjectService]

})
export class AddProjectComponent 
{
  title = 'Project Manager';
  data: any;
  orderByData:string='startDate';
  reverse : boolean = false;
  visible : boolean = false;
  checkBoxVal : boolean = false;
  project: Project=new Project();
  userId: number;
  firstName:string;
  lastName:string;
  priority:number=15;
  bsConfig: Partial<BsDatepickerConfig>;
  options: Options = {
    floor: 0,
    ceil: 30,
    step: 1,
    noSwitching: false
  };

  constructor(public projectService : ProjectService,
    private _ngZone: NgZone,private router:Router)
  {
    this.bsConfig=Object.assign({
      containerClass : 'theme-default',
      dateInputFormat : 'DD-MM-YYYY'
    });
  }

  toggle()
  {
    this.checkBoxVal=!this.checkBoxVal;
  }

  ngOnInit()
    {
      this.projectService.getAllProjects().subscribe(
        resp=>{this.data=resp},error=>{console.log(error,"error")}
      );
    }

    setOrder(event)
    {
      var buttonName= event.target.name;
        if(this.orderByData===buttonName)
        {
          
          this.reverse=!this.reverse;
        }
        else
        {
          this.orderByData=buttonName;
        }

      
    }

}

