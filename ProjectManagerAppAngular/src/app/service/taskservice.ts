import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Project } from '../model/project.model';
import { Task } from '../model/task.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';
import { NgForm } from '@angular/forms';

@Injectable()
export class TaskService
{
    constructor(private http : HttpClient)
    {

    }

     updateTask(updatedTask:Task): Observable<any>
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/tasks",updatedTask);
    
    }

    createTask(newTask:Task) : Observable<any>
    {
        return this.http.post("http://localhost:8181/ProjectManagerApp/tasks",newTask);
        
    }

    endTask(taskId:number)
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/tasks/endtask/"+taskId,null); 
    }

    getTasks(projectId:number)
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/tasks/project/"+projectId);
    }


}