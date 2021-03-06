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
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'projectmanagerapp-displayprojects',
  templateUrl: '../html/projectmanagerapp.edittask.component.html',
  styleUrls: ['../app.component.css'],
  providers: [ProjectService,UsersService,TaskService,ParentTaskService]

})
export class EditTaskComponent 
{
  title = 'Edit Task';
  data: any;
  users : any;
  taskData :  any;
  taskId : number;
  isParentTask : number;
  parentTaskData:any;
  orderByData:string='startDate';
  reverse : boolean = false;
  visible : boolean = false;
  checkBoxVal : boolean = false;
  project: Project=new Project();
  task: Task=new Task();
  parentTask: ParentTask=new ParentTask();
  updatedParentTask: ParentTask=new ParentTask();
  taskUserId: number;
  projectId: number;
  taskOwnerName:string;
  taskOwnerNameTemp:string;
  parentTaskName:string;
  parentTaskNameTemp:string;
  priority:number= 0;
  bsConfig: Partial<BsDatepickerConfig>;
  options: Options = {
    floor: 0,
    ceil: 30,
    step: 1,
    noSwitching: false
  };
  status: string;
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

  clearForm()
  {
    this.priority=0;
    this.startDate=null;
    this.endDate=null;
    this.taskUserId=null;
    this.taskOwnerName=null;
    this.taskOwnerNameTemp=null;
    this.taskDesc=null;
  }

  addTaskManager(user:any)
  {
    this.taskOwnerNameTemp=user.firstName+' '+user.lastName;
    this.taskUserId=user.userId;
  }

  assignFn()
  {
    this.taskOwnerName=this.taskOwnerNameTemp;
  }

  constructor(public projectService : ProjectService,
    private _ngZone: NgZone,private route:ActivatedRoute,private router:Router,public usersService:UsersService,
    public taskService : TaskService,public parentTaskService: ParentTaskService)
  {
    this.bsConfig=Object.assign({
      containerClass : 'theme-default',
      dateInputFormat : 'DD-MM-YYYY'
    });
  }

  toggle()
  {
    this.checkBoxVal=!this.checkBoxVal;
    if(this.checkBoxVal)
    {
      this.startDate=null;
      this.endDate=null;
      this.options={
        floor: 0,
        ceil: 30,
        step: 1,
        disabled:true,
        noSwitching: false
      };
      this.priority=0;
    }
    else
    {
      this.startDate=new Date();
      this.endDate=new Date();
      this.endDate.setDate(this.endDate.getDate() + 1);
      this.options={
        floor: 0,
        ceil: 30,
        step: 1,
        disabled:false,
        noSwitching: false};
    }
  }

  ngOnInit()
    {
      this.route.params.subscribe(params=>
        {
          this.taskId=+params['taskId'];
        });

        this.taskService.getATask(this.taskId).subscribe(
          resp=>{
            this.taskData=resp;
            this.taskDesc=this.taskData.task;
            this.status=this.taskData.status;
      this.projectDesc=this.taskData.project.project;
      this.projectDescTemp=this.taskData.project.project;
      this.projectId=this.taskData.project.projectId;
      this.isParentTask=this.taskData.isParentTask;
      if(!(this.taskData.parentTask===null) && this.isParentTask===0)
      {

        this.parentTaskDesc=this.taskData.parentTask.parentTaskDesc;
        this.parentTaskDescTemp=this.taskData.parentTask.parentTaskDesc;
        this.parentTaskId=this.taskData.parentTask.id;
      }
      else
      {
        this.parentTaskId=this.taskData.parentTask.id;
      }
      if(this.isParentTask==0)
      {
      this.usersService.getAllUsers().subscribe(
        resp=>{this.users=resp},error=>{console.log(error,"error")}
      );
      this.parentTaskService.getAllParentTaskOfProjects(this.projectId).subscribe(
        resp=>{this.parentTaskData=resp},error=>{console.log(error,"error")}
      );
      }
      else
      {
        this.checkBoxVal=true;
      }
      this.taskOwnerName=this.taskData.taskManager.firstName+' '+this.taskData.taskManager.lastName;
      this.taskOwnerNameTemp=this.taskData.taskManager.firstName+' '+this.taskData.taskManager.lastName;
      this.taskUserId=this.taskData.taskManager.userId;
      this.priority=this.taskData.priority;
      let startDateData=this.taskData.startDate.split('-');
      this.startDate=new Date(startDateData[1]+'-'+startDateData[0]+'-'+startDateData[2]);
      let endDateData=this.taskData.endDate.split('-');
      this.endDate=new Date(endDateData[1]+'-'+endDateData[0]+'-'+endDateData[2]);
          },error=>{console.log(error,"error")}
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

    updateTask()
    { 
      if(this.isParentTask==0)
      {
        this.task.task=this.taskDesc;
        this.task.taskId=this.taskId;
        this.task.status=this.status;
        this.task.project=new Project();
        this.task.project.projectId=this.projectId;
        this.task.priority=this.priority;
        if(this.parentTaskDescTemp!=null && this.parentTaskDescTemp!=''
        )
        {
          this.task.parentTask=new ParentTask();
          this.task.parentTask.id=this.parentTaskId;
        }
        else
        {
          this.task.parentTask=null;
        }
        this.task.taskManager=new Users();
        this.task.taskManager.userId=this.taskUserId;

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

      this.task.startDate=dayString+'-'+monthString+'-'+dateData.getFullYear();
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

      this.task.endDate=dayString+'-'+monthString+'-'+dateData.getFullYear();

      let startDateData=this.task.startDate.split('-');
      let endDateData=this.task.endDate.split('-');
      let startDateComparison = new Date(startDateData[1]+'-'+startDateData[0]+'-'+startDateData[2]);
      let endDateComparison = new Date(endDateData[1]+'-'+endDateData[0]+'-'+endDateData[2]);

      if(endDateComparison>=startDateComparison)
      {
          this.taskService.updateTask(this.task).subscribe(
            resp=>
            {
            },
            error=>{console.log(error,"error")}
          );
          this.router.navigate(['/viewtask']);

      }
      else
      {
        alert('Task End Date should be greater than Start Date');
      }


      }
      else
      {
          this.updatedParentTask.id=this.parentTaskId;
          this.updatedParentTask.parentTaskDesc=this.taskDesc;
          this.updatedParentTask.project= new Project();
          this.updatedParentTask.project.projectId=this.projectId;
          this.parentTaskService.updateParentTask(this.updatedParentTask).subscribe(
            resp=>
            {
            },
            error=>{console.log(error,"error")}
          );

        this.task= new Task();
        this.task.task=this.taskDesc;
        this.task.taskId=this.taskId;
        this.task.status=this.status;
        this.task.project=new Project();
        this.task.project.projectId=this.projectId;
        this.taskService.updateTask(this.task);
          this.router.navigate(['/viewtask']);
      }
        
            
}
}