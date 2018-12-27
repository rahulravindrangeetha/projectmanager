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
import { DatePipe } from '@angular/common';
import { AmazingTimePickerService} from 'amazing-time-picker';
import { NgZone } from '@angular/core';

@Component({
  selector: 'workoutapp-startworkout',
  templateUrl: '../html/workoutapp.endworkout.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutService,WorkoutCategoryService]
})
export class WorkoutEndComponent 
{
  title : string;
  data: any;
  category: any;
  workoutId: number;
  workoutTitle: string;
  bsConfig: Partial<BsDatepickerConfig>;
  endDate: Date;
  endTime: string;
  day : number;
  month : number;
  dayString: string;
  monthString: string;
  hour:number;
  hourString:string;
  minutes:number;
  minutesString:string;
  workoutActive: WorkoutActive = new WorkoutActive();
  displayWorkoutComponent :WorkoutDisplayComponent;
  
    constructor(public workoutService : WorkoutService,
      public workoutCategoryService : WorkoutCategoryService,
    private route:ActivatedRoute,private router:Router,
    private datePipe: DatePipe,
    private _ngZone: NgZone)
    {
      this.bsConfig=Object.assign({
        containerClass : 'theme-default',
        dateInputFormat : 'DD-MM-YYYY'
      });
      
    }

    endWorkout(endWorkoutForm :NgForm,workoutActive: any)
    {

      this.workoutActive.status='FALSE';
      this.workoutActive.startDate=workoutActive.startDate;
      this.workoutActive.startTime=workoutActive.startTime;
      this.workoutActive.comment=endWorkoutForm.value.comment;
      let dateData =endWorkoutForm.value.endDate;
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
      this.workoutActive.endDate=this.dayString+'-'+this.monthString+'-'+dateData.getFullYear();
     
     let timeData=endWorkoutForm.value.endTime.split(':');
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
      this.workoutActive.endTime=this.hourString+':'+this.minutesString;
      let startDateComparison = new Date(this.workoutActive.startDate.split('-').reverse().join('-')+'T'+this.workoutActive.startTime);
      let endDateComparison = new Date(this.workoutActive.endDate.split('-').reverse().join('-')+'T'+this.workoutActive.endTime);
      if(endDateComparison>=startDateComparison)
      {
      this.workoutService.endWorkout(this.workoutActive,this.workoutId).subscribe(
        resp=>{this.router.navigate(['/viewall'])},error=>{console.log(error,"error")}
      );
      }
      else
      {
        alert('Workout End Time cannot be less than Workout Start Time');
      }
    }

    checkDateAndTime(endWorkoutForm :NgForm,workoutActive: any) :boolean
    {
      return 
      return true;
    }

    ngOnInit()
    {
      this.route.params.subscribe(params=>
        {
          this.workoutId=+params['workoutId'];
          this.workoutTitle=params['workoutTitle'];
        });

        this.title='End Workout';

        try
      {

        this.workoutService.getActiveWorkoutDetail(this.workoutId).
        subscribe(resp=>{
          this.data=resp;
        },error=>{console.log(error,"error")});

        alert(new Date(this.data.startDate));
      }
      catch(e)
      {
        console.log(e);
      }

      this.endDate=new Date();
        this.endTime=this.datePipe.transform(this.endDate,'H:mm');
        
    }
  
}
