import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Project } from '../model/project.model';
import { EmptyObservable } from 'rxjs/observable/EmptyObservable';
import { NgForm } from '@angular/forms';

@Injectable()
export class ProjectService
{
    constructor(private http : HttpClient)
    {

    }

    getAllProjects()
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/projects");
    }

    getProject(projectId: number)
    {
        return this.http.get("http://localhost:8181/ProjectManagerApp/projects/"+projectId);
    }

    updateProject(updatedProject:Project): Observable<any>
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/projects/"+updatedProject.projectId,updatedProject);
    
    }

    createProject(newProject:Project) : Observable<any>
    {
        return this.http.post("http://localhost:8181/ProjectManagerApp/projects",newProject);
        
    }

    suspendProject(projectId:number) : Observable<any>
    {
        return this.http.put("http://localhost:8181/ProjectManagerApp/projects/suspend/"+projectId,null);
        
    }
}