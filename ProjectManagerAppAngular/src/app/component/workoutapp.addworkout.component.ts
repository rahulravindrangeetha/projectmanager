import { Component } from '@angular/core';
import { WorkoutService} from '../service/workoutservice'
import { WorkoutCategoryService} from '../service/workoutcategoryservice'
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { WorkoutCollection} from '../model/workoutcollection.model';
import { WorkoutCategory} from '../model/workoutcategory.model';
import { NgForm } from '@angular/forms';
import { NgZone } from '@angular/core';


@Component({
  selector: 'workoutapp-editworkout',
  templateUrl: '../html/workoutapp.newworkout.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutService,WorkoutCategoryService]
})
export class WorkoutAddComponent 
{
  heading : string;
  data: any;
  category: any;
  calBurnPerMin : string;
  workoutId: number;
  workoutCollection : WorkoutCollection = new WorkoutCollection();
  workoutCategory : WorkoutCategory = new WorkoutCategory();
  
    constructor(public workoutService : WorkoutService,
      public workoutCategoryService : WorkoutCategoryService,
    private route:ActivatedRoute,private router:Router,
    private _ngZone: NgZone)
    {}
  
    ngOnInit()
    {
      this.getFormData();
      this.heading = 'Add Workout';
    }

    increaseCalorie(calorieBurnPerMin:string):string
    {
      let intValue =+calorieBurnPerMin;
      intValue=intValue + .1;
      return parseFloat(intValue.toString()).toFixed(2).toString();
    }

    decreaseCalorie(calorieBurnPerMin:string):string
    {
      let intValue =+calorieBurnPerMin;
      if(intValue - .1>0.00)
      {
        intValue=intValue - .1;
        return parseFloat(intValue.toString()).toFixed(2).toString();
      }
      else
      {
        return parseFloat(intValue.toString()).toFixed(2).toString();
      }
    }
 
    getFormData()
    {
      try
      {
        this.calBurnPerMin='0.00';
        this.workoutCategoryService.getAllCategories().
        subscribe(resp=>{this.category=resp},error=>{console.log(error,"error")});
      }
      catch(e)
      {
        console.log(e);
      }
  
    }

    addNewWorkout(newWorkoutForm : NgForm):void
    {
      if(+newWorkoutForm.value.calBurnPerMin>0)
      {
      this.workoutCollection.calBurnPerMin=newWorkoutForm.value.calBurnPerMin;
      this.workoutCollection.workoutCategory=this.workoutCategory;
      this.workoutCollection.workoutNote=newWorkoutForm.value.note;
      this.workoutCollection.workoutTitle=newWorkoutForm.value.workoutTitle;
      this.workoutCollection.workoutCategory.categoryId=newWorkoutForm.value.category;
      this.workoutService.addNewWorkout(this.workoutCollection).subscribe(
        resp=>{this.router.navigate(['/viewall'])},error=>{console.log(error,"error")}
      );  
      }
      else
      {
        alert('Invalid value for calories burn per min !!!');
      }   

    }
  

}
