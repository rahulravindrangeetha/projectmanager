import { Users } from "./users.model";
import { ParentTask } from "./parenttask.model";
import { Project } from "./project.model";
export  class Task
{
    taskId : number;
    task : string;
    taskManager : Users;
    startDate : string;
    endDate : string;
    priority : number;
    parentTask : ParentTask;
    project : Project;
    status : string;
    

} 