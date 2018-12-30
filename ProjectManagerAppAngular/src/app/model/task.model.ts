import { Users } from "./users.model";
import { ParentTask } from "./parenttask.model";
export  class Task
{
    taskId : number;
    task : string;
    taskManager : Users;
    startDate : string;
    endDate : string;
    priority : number;
    parentTask : ParentTask;
    status : string;
    

} 