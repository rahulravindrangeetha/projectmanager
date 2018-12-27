import { Component } from '@angular/core';
import { WorkoutCategoryService} from '../service/workoutcategoryservice'
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { WorkoutCategory} from '../model/workoutcategory.model';
import { NgForm } from '@angular/forms';
import { CategoryfilterPipe} from '../categoryfilter.pipe';
import { NgZone } from '@angular/core';


@Component({
  selector: 'workoutapp-editworkout',
  templateUrl: '../html/workoutapp.workoutCategory.html',
  styleUrls: ['../app.component.css'],
  providers: [WorkoutCategoryService]
})
export class WorkoutCategoryShowComponent 
{
  title : string;
  data: any;
  workoutCategory : WorkoutCategory = new WorkoutCategory();
  
    constructor(public workoutCategoryService : WorkoutCategoryService,
    private route:ActivatedRoute,private router:Router,
    private _ngZone: NgZone)
    {

    }
  
    ngOnInit()
    {
      this.getFormData();
      this.title='Add Category';
    }
 
    getFormData()
    {
      try
      {
        this.workoutCategoryService.getAllCategories().
        subscribe(resp=>{this.data=resp},error=>{console.log(error,"error")});
      }
      catch(e)
      {
        console.log(e);
      }
  
    }

    deleteCategory(categoryId:number)
    {
      if(confirm('Are you sure you want to delete this category ? Associated workout records will also get deleted'))
      {
        this.router.navigate(['/workoutCategory/delete/'+categoryId])
      }
    }
  

}
