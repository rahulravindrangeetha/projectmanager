import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { WorkoutCategory } from '../model/workoutcategory.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';

@Injectable()
export class WorkoutCategoryService
{
    constructor(private http : HttpClient)
    {

    }

    getAllCategories()
    {
        return this.http.get("http://localhost:8181/WorkoutApplication/workoutcategory");
    }

    updateCategory(workoutCategory : WorkoutCategory): Observable<any>
    {
         return this.http.put("http://localhost:8181/WorkoutApplication/workoutcategory",workoutCategory);
    }


    deleteCategory(workoutCategoryId : number): Observable<any>
    {
        return this.http.delete("http://localhost:8181/WorkoutApplication/workoutcategory/"+workoutCategoryId);

    }

    createCategory(workoutCategory : WorkoutCategory): Observable<any>
    {
         return this.http.post("http://localhost:8181/WorkoutApplication/workoutcategory",workoutCategory);
    }
}