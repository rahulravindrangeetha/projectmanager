import { Component } from '@angular/core';
import { ProjectManagerHyperLinkComponent } from './projectmanagerapp.hyperlink.component';
import { ProjectService} from '../service/projectservice'
import { ProjectfilterPipe } from '../projectfilter.pipe';
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { OrderModule } from 'ngx-order-pipe';
import { Project } from '../model/project.model';
import { Users } from '../model/users.model';
import { BsDatepickerConfig } from '../../../node_modules/ngx-bootstrap/datepicker';
import { Options } from 'ng5-slider';
import { UsersService} from '../service/usersservice'
import { UserfilterPipe } from '../userfilter.pipe';


@Component({
  selector: 'projectmanagerapp-displayprojects',
  templateUrl: '../html/projectmanagerapp.addproject.component.html',
  styleUrls: ['../app.component.css'],
  providers: [ProjectService,UsersService]

})
export class AddProjectComponent 
{
  title = 'Project Manager';
  data: any;
  users : any;
  orderByData:string='startDate';
  reverse : boolean = false;
  visible : boolean = false;
  checkBoxVal : boolean = false;
  project: Project=new Project();
  managerUserId: number;
  projectId: number;
  managerName:string;
  managerNameTemp:string;
  priority:number= 0;
  bsConfig: Partial<BsDatepickerConfig>;
  searchProjectVal:string='';
  options: Options = {
    floor: 0,
    ceil: 30,
    step: 1,
    noSwitching: false
  };

  projectDesc: string;
  startDate:Date;
  endDate:Date;
  startDateString:string;
  endDateString:string;
  addManager(user:any)
  {
    this.managerNameTemp=user.firstName+' '+user.lastName;
    this.managerUserId=user.userId;
  }

  assignFn()
  {
    this.managerName=this.managerNameTemp;
  }

  constructor(public projectService : ProjectService,
    private _ngZone: NgZone,private router:Router,public usersService:UsersService)
  {
    this.bsConfig=Object.assign({
      containerClass : 'theme-default',
      dateInputFormat : 'DD-MM-YYYY'
    });
  }

  toggle()
  {
    this.checkBoxVal=!this.checkBoxVal;
    if(!this.checkBoxVal)
    {
      this.startDate=null;
      this.endDate=null;
    }
    else
    {
      this.startDate=new Date();
      this.endDate=new Date();
      this.endDate.setDate(this.endDate.getDate() + 1);
    }
  }

  ngOnInit()
    {
      this.usersService.getAllUsers().subscribe(
        resp=>{this.users=resp},error=>{console.log(error,"error")}
      );
      this.projectService.getAllProjects().subscribe(
        resp=>{this.data=resp},error=>{console.log(error,"error")}
      );
    }

    resetForm()
    {
      this.projectDesc=null;
      this.startDate=null;
      this.endDate=null;
      this.managerUserId=null;
      this.managerName=null;
      this.managerNameTemp=null;
      this.priority=0;
      this.checkBoxVal=false;
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

    addProject()
    {

      this.project.project=this.projectDesc;
      this.project.priority=this.priority;

      this.project.projectManager= new Users();
      this.project.projectManager.userId=this.managerUserId;
      if(this.checkBoxVal)
      {

      var dateData,month,monthString,day,dayString;
      dateData =this.startDate;
      month=dateData.getMonth()+1;
      if(month<10)
      {
        monthString='0'+month.toString();
      }
      else
      {
        monthString=month.toString();
      }
      day=dateData.getDate();
      if(day<10)
      {
        dayString='0'+day.toString();
      }
      else
      {
        dayString=day.toString();
      }

      this.project.startDate=dayString+'-'+monthString+'-'+dateData.getFullYear();

      dateData =this.endDate;
      month=dateData.getMonth()+1;
      if(month<10)
      {
        monthString='0'+month.toString();
      }
      else
      {
        monthString=month.toString();
      }
      day=dateData.getDate();
      if(day<10)
      {
        dayString='0'+day.toString();
      }
      else
      {
        dayString=day.toString();
      }

      this.project.endDate=dayString+'-'+monthString+'-'+dateData.getFullYear();


    let startDateComparison = new Date(this.project.startDate);
    let endDateComparison = new Date(this.project.endDate);

 

    if(endDateComparison>=startDateComparison)
    {

    this.projectService.createProject(this.project).subscribe(
      resp=>{
        this.resetForm();
        this.ngOnInit();
        
      },
      error=>
      {console.log(error,"error")
    }
    );
    }
    else
    {
      alert('Project End Date should be greater than Start Date');
    }
    }
    else
    {
      this.projectService.createProject(this.project).subscribe(
        resp=>{
          this.resetForm();
          this.ngOnInit();
          
        },
        error=>
        {console.log(error,"error")
      }
      );
    }

}

editProject(editProject:Project)
{
  
  this.projectDesc=editProject.project;
  this.projectId=editProject.projectId
  this.priority=editProject.priority;
  this.managerUserId=editProject.projectManager.userId;
  this.managerName=editProject.projectManager.firstName+' '+editProject.projectManager.lastName;
  this.visible =!this.visible;
  if(editProject.startDate!=null)
  {
    let startDateVar= editProject.startDate.split('-');
    let endDateVar= editProject.endDate.split('-');
    this.startDate = new Date(startDateVar[1]+'-'+startDateVar[0]+'-'+startDateVar[2]);
    this.endDate = new Date(endDateVar[1]+'-'+endDateVar[0]+'-'+endDateVar[2]);
    this.checkBoxVal=true;
  }
  else
  {
    this.startDate=null;
    this.endDate=null;
    this.checkBoxVal=false;

  }
  window.scroll(0,0);
}

updateProject()
{
  this.project=new Project();
  this.project.projectManager=new Users();
  this.project.projectId=this.projectId;
  this.project.project=this.projectDesc;
  this.project.priority=this.priority;
  this.project.projectManager.userId=this.managerUserId;
  if(this.checkBoxVal)
  {
    var dateData,month,monthString,day,dayString;
      dateData =this.startDate;
      month=dateData.getMonth()+1;
      if(month<10)
      {
        monthString='0'+month.toString();
      }
      else
      {
        monthString=month.toString();
      }
      day=dateData.getDate();
      if(day<10)
      {
        dayString='0'+day.toString();
      }
      else
      {
        dayString=day.toString();
      }

      this.project.startDate=dayString+'-'+monthString+'-'+dateData.getFullYear();

      dateData =this.endDate;
      month=dateData.getMonth()+1;
      if(month<10)
      {
        monthString='0'+month.toString();
      }
      else
      {
        monthString=month.toString();
      }
      day=dateData.getDate();
      if(day<10)
      {
        dayString='0'+day.toString();
      }
      else
      {
        dayString=day.toString();
      }

      this.project.endDate=dayString+'-'+monthString+'-'+dateData.getFullYear();


    let startDateComparison = new Date(this.project.startDate);
    let endDateComparison = new Date(this.project.endDate);

 

    if(endDateComparison>=startDateComparison)
    {

    this.projectService.updateProject(this.project).subscribe(
      resp=>{
        this.projectId=null;
        this.visible=false;
        this.resetForm();
        this.ngOnInit();
        
      },
      error=>
      {console.log(error,"error")
    }
    );
    }
    else
    {
      alert('Project End Date should be greater than Start Date');
    }
    
  
}
else
  {
    this.project.startDate=null;
    this.project.endDate=null;
    this.projectService.updateProject(this.project).subscribe(
      resp=>{
        this.projectId=null;
        this.visible=false;
        this.resetForm();
        this.ngOnInit();
        
      },
      error=>
      {console.log(error,"error")
    }
    );
  }
}

suspendProject(projectId:number)
{
  this.projectService.suspendProject(projectId).subscribe(
    resp=>{
      this.projectId=null;
      this.visible=false;
      this.resetForm();
      this.ngOnInit();
      
    },
    error=>
    {console.log(error,"error")
  }
  );

}
}