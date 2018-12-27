import { Component } from '@angular/core';
import { WorkoutService} from '../service/workoutservice'
import { WorkoutCategoryService} from '../service/workoutcategoryservice'
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { WorkoutCollection } from '../model/workoutcollection.model';
import { WorkoutCategory } from '../model/workoutcategory.model';
import { NgForm } from '@angular/forms';
import { NgZone } from '@angular/core';


@Component({
  selector: 'workoutapp-editworkout',
  templateUrl: '../html/workoutapp.editworkout.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutService,WorkoutCategoryService]
})
export class WorkoutEditComponent 
{
  title : string;
  data: any;
  category: any;
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
      this.getTheWorkoutData();
      this.title='Edit Workout';
    }

    updateWorkout(editWorkoutForm : NgForm,data : any)
    {
      this.workoutCollection.workoutCategory=this.workoutCategory;
      this.workoutCollection.workoutId=data.workoutId;
      this.workoutCollection.calBurnPerMin=data.calBurnPerMin;
      this.workoutCollection.workoutNote=editWorkoutForm.value.note;
      this.workoutCollection.workoutTitle=editWorkoutForm.value.title;
      this.workoutCollection.workoutCategory.categoryId=editWorkoutForm.value.category;
      this.workoutService.updateWorkout(this.workoutCollection).subscribe(
        resp=>{this.router.navigate(['/viewall'])},error=>{console.log(error,"error")}
      );
        
    }
  
    getTheWorkoutData()
    {
      this.route.params.subscribe(params=>
        {
          this.workoutId=+params['workoutId'];

        });
      try
      {

        this.workoutService.getAWorkout(this.workoutId).
        subscribe(resp=>{this.data=resp},error=>{console.log(error,"error")});

        this.workoutCategoryService.getAllCategories().
        subscribe(resp=>{this.category=resp},error=>{console.log(error,"error")});
      }
      catch(e)
      {
        console.log(e);
      }
  
  
  
    }
  

}
