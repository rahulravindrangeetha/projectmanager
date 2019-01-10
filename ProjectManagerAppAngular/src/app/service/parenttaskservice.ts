import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Project } from '../model/project.model';
import { Task } from '../model/task.model';
import { ParentTask } from '../model/parenttask.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';
import { NgForm } from '@angular/forms';

@Injectable()
export class ParentTaskService
{
    constructor(private http : HttpClient)
    {

    }


    createParentTask(newParentTask:ParentTask) : Observable<any>
    {
        return this.http.post("http://localhost:8181/ProjectManagerApp/parenttasks",newParentTask);
        
    }

    getAllParentTaskOfProjects(projectId:number)
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/parenttasks/"+projectId); 
    }

    updateParentTask(updateParentTask:ParentTask) : Observable<any>
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/parenttasks",updateParentTask);
        
    }
}