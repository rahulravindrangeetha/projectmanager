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
import { ParentTask } from '../model/parenttask.model';
import { Task } from '../model/task.model';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker/ngx-bootstrap-datepicker';
import { Options } from 'ng5-slider';
import { UsersService} from '../service/usersservice'
import { TaskService} from '../service/taskservice'
import { ParentTaskService} from '../service/parenttaskservice'
import { UserfilterPipe } from '../userfilter.pipe';


@Component({
  selector: 'projectmanagerapp-displayprojects',
  templateUrl: '../html/projectmanagerapp.viewtask.component.html',
  styleUrls: ['../app.component.css'],
  providers: [ProjectService,UsersService,TaskService,ParentTaskService]

})
export class ViewTaskComponent 
{
  title = 'View Task';
  data: any;
  users : any;
  taskData:any=null;
  orderByData:string='startDate';
  reverse : boolean = false;
  visible : boolean = false;
  checkBoxVal : boolean = false;
  project: Project=new Project();
  task: Task=new Task();
  parentTask: ParentTask=new ParentTask();
  taskUserId: number;
  projectId: number;
  taskOwnerName:string;
  taskOwnerNameTemp:string;
  parentTaskName:string;
  parentTaskNameTemp:string;
  priority:number= 0;
  projectDesc: string;
  projectDescTemp: string;
  startDate:Date;
  endDate:Date;
  startDateString:string;
  endDateString:string;
  taskDesc:string;
  parentTaskId:number;
  parentTaskDesc: string;
  parentTaskDescTemp: string;

  addProjectData(projectObj:Project)
  {
    this.projectDescTemp=projectObj.project;
    this.projectId=projectObj.projectId;
  }

  assignProjectFn()
  {
    this.projectDesc=this.projectDescTemp;
    this.taskService.getTasks(this.projectId).subscribe(
      resp=>{this.taskData=resp},error=>{console.log(error,"error")}
    );
  }

  constructor(public projectService : ProjectService,
    private _ngZone: NgZone,private router:Router,public usersService:UsersService,
    public taskService : TaskService,public parentTaskService: ParentTaskService)
  {
   
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