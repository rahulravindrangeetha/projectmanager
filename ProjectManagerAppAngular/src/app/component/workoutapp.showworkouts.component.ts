import { Component } from '@angular/core';
import { WorkoutService} from '../service/workoutservice'
import { WorkoutfilterPipe } from '../workoutfilter.pipe'; 
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'workoutapp-displayworkouts',
  templateUrl: '../html/workoutapp.displayworkout.component.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutService]

})
export class WorkoutDisplayComponent 
{
  data: any;
  
  constructor(public workoutService : WorkoutService,
    private _ngZone: NgZone,private router:Router)
  {
  }

  ngOnInit()
  {
     this.getAllWorkouts();
  }

  getAllWorkouts()
  {
    console.log('in getAllWorkouts ');
    try
    {
    this.workoutService.getAllWorkouts().
    subscribe(resp=>{this.data=resp},error=>{console.log(error,"error")});
    }
    catch(e)
    {
      console.log(e);
    }



  }

  deleteWorkout(workoutId:number)
  {
    if(confirm('Are you sure you want to delete this workout ? All past workout records would be deleted'))
    {
      this.router.navigate(['/workout/delete/'+workoutId]);
    }
  }



  
}
