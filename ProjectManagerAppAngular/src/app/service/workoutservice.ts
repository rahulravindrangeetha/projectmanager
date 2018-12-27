import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { WorkoutActive } from '../model/workoutactive.model';
import { WorkoutCollection } from '../model/workoutcollection.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';

@Injectable()
export class WorkoutService
{
    constructor(private http : HttpClient)
    {

    }

    getAllWorkouts()
    {
        return this.http.get("http://localhost:8181/WorkoutApplication/workout");
    }

    getAWorkout(workoutId: number)
    {
        return this.http.get("http://localhost:8181/WorkoutApplication/workout/"+workoutId);
    }

    deleteWorkout(workoutId: number):Observable<any>
    {
        return this.http.delete("http://localhost:8181/WorkoutApplication/workout/"+workoutId);
    }

    updateWorkout(data:WorkoutCollection): Observable<any>
    {
        console.log("in service update workout")
        return this.http.put("http://localhost:8181/WorkoutApplication/workout",data);
    
    }

    startWorkout(data:WorkoutActive ,workoutId:number): Observable<any>
    {
        return  this.http.post("http://localhost:8181/WorkoutApplication/workout/startWorkout/"+workoutId,data);

    }

    getActiveWorkoutDetail(workoutId:number)
    {
        return this.http.get("http://localhost:8181/WorkoutApplication/workout/startWorkout/"+workoutId);
    }

    endWorkout(data:WorkoutActive ,workoutId:number): Observable<any>
    {
        return this.http.put("http://localhost:8181/WorkoutApplication/workout/endWorkout/"+workoutId,data);

    }


    addNewWorkout(data:WorkoutCollection) : Observable<any>
    {
        return this.http.post("http://localhost:8181/WorkoutApplication/workout",data);
        
    }
}