import { Component } from '@angular/core';
import { WorkoutService} from '../service/workoutservice'
import { WorkoutCategoryService} from '../service/workoutcategoryservice'
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { BsDatepickerConfig } from '../../../node_modules/ngx-bootstrap/datepicker';
import { TimepickerConfig } from '../../../node_modules/ngx-bootstrap/timepicker';
import { NgForm } from '@angular/forms';
import { WorkoutActive} from '../model/workoutactive.model';
import { WorkoutDisplayComponent} from '../component/workoutapp.showworkouts.component';
import { AmazingTimePickerService} from 'amazing-time-picker';
import { DatePipe } from '@angular/common';
import { NgZone } from '@angular/core';

@Component({
  selector: 'workoutapp-startworkout',
  templateUrl: '../html/workoutapp.startworkout.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutService,WorkoutCategoryService]
})
export class WorkoutStartComponent 
{
  title : string;
  data: any;
  category: any;
  workoutId: number;
  workoutTitle: string;
  startDate : Date;
  startTime : string;
  day : number;
  month : number;
  dayString: string;
  monthString: string;
  hour:number;
  hourString:string;
  minutes:number;
  minutesString:string;
  bsConfig: Partial<BsDatepickerConfig>;
  workoutActive: WorkoutActive = new WorkoutActive();
  displayWorkoutComponent :WorkoutDisplayComponent;
  
    constructor(public workoutService : WorkoutService,
      public workoutCategoryService : WorkoutCategoryService,
    private route:ActivatedRoute,private router:Router,
    private atp:AmazingTimePickerService,
    private datePipe: DatePipe,
    private _ngZone: NgZone)
    {
      this.bsConfig=Object.assign({
        containerClass : 'theme-default',
        dateInputFormat : 'DD-MM-YYYY'
      });

    }

    startWorkout(startWorkoutForm :NgForm)
    {
      this.workoutActive.status='TRUE';
      let dateData =startWorkoutForm.value.startDate;
      this.month=dateData.getMonth()+1;
      if(this.month<10)
      {
        this.monthString='0'+this.month.toString();
      }
      else
      {
        this.monthString=this.month.toString();
      }
      this.day=dateData.getDate();
      if(this.day<10)
      {
        this.dayString='0'+this.day.toString();
      }
      else
      {
        this.dayString=this.day.toString();
      }

      this.workoutActive.startDate=this.dayString+'-'+this.monthString+'-'+dateData.getFullYear();
      let timeData=startWorkoutForm.value.startTime.split(':');
      this.hour=+timeData[0];
      this.minutes=+timeData[1];
      if(this.minutes<10)
      {
        this.minutesString='0'+this.minutes;
      }
      else
      {
        this.minutesString=this.minutes.toString();
      }
      if(this.hour<10)
      {
        this.hourString='0'+this.hour;
      }
      else
      {
        this.hourString=this.hour.toString();
      } 
      this.workoutActive.startTime=this.hourString+':'+this.minutesString;
  
      this.workoutActive.comment=startWorkoutForm.value.comment;
      this.workoutService.startWorkout(this.workoutActive,this.workoutId).subscribe(
        resp=>{this.router.navigate(['/viewall'])},error=>{console.log(error,"error")}
      );  
    }

    ngOnInit()
    {
      this.route.params.subscribe(params=>
        {
          this.workoutId=+params['workoutId'];
          this.workoutTitle=params['workoutTitle'];
        });
        this.startDate=new Date();
        this.startTime=this.datePipe.transform(this.startDate,'H:mm');
        this.title='Start Workout';
        
    }

    open()
    {
      const amazingTimePicker = this.atp.open();
      amazingTimePicker.afterClose().subscribe(time=>{
      }

      )
    }
  
}
