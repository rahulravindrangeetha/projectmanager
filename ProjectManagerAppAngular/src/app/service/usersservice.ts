import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Users } from '../model/users.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';
import { NgForm } from '@angular/forms';

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

    deleteUser(userId: number):Observable<any>
    {
        return this.http.delete("http://localhost:8181/ProjectManagerApp/users/"+userId);
    }

    updateUser(updatedUser:Users): Observable<any>
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/users",updatedUser);
    
    }

    createNewUser(newUser:Users) : Observable<any>
    {
        return this.http.post("http://localhost:8181/ProjectManagerApp/users",newUser);
        
    }
}