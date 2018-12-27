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
export class WorkoutDeleteComponent 
{
  title : 'Edit Workout';
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
      this.route.params.subscribe(params=>
        {
          this.workoutId=+params['workoutId'];

        });
        this.workoutService.deleteWorkout(this.workoutId).subscribe(
          resp=>{this.router.navigate(['/viewall'])},error=>{console.log(error,"error")}
        );
        
    }

}
