import { Component } from '@angular/core';
import { WorkoutCategoryService} from '../service/workoutcategoryservice'
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { WorkoutCollection} from '../model/workoutcollection.model';
import { WorkoutCategory} from '../model/workoutcategory.model';
import { NgForm } from '@angular/forms';
import { NgZone } from '@angular/core';


@Component({
  selector: 'workoutapp-editworkout',
  templateUrl: '../html/workoutapp.workoutCategory.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutCategoryService]
})
export class WorkoutCategoryCreateComponent 
{
  data : any;
  title : string;
  createCategoryName :string;

  workoutCategory : WorkoutCategory = new WorkoutCategory();
  
    constructor(public workoutCategoryService : WorkoutCategoryService,
    private route:ActivatedRoute,private router:Router,
    private _ngZone: NgZone)
    {}
  
    ngOnInit()
    {
      this.route.params.subscribe(params=>
        {
          this.workoutCategory.categoryName=params['categoryName'];;
        });
        this.title='Add Category';
        try
      {

   
        this.workoutCategoryService.createCategory(this.workoutCategory).subscribe(
          resp=>{this.router.navigate(['/category'])},error=>{console.log(error,"error")}
        );  
      }
  catch(e)
      {
        console.log(e);
      }
    }




}
