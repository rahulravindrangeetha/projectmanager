import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Users } from '../model/users.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';

@Injectable()
export class UsersService
{
    constructor(private http : HttpClient)
    {

    }

    getAllUsers()
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/users");
    }

    getAUser(userId: number)
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/users/"+userId);
    }

    deleteUser(userToBeDeleted: Users):Observable<any>
    {
        return this.http.delete("http://localhost:8181/ProjectManagerApp/users",userToBeDeleted);
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