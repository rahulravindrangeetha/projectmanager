import { Users } from "./users.model";
import { Project } from "./project.model";
export  class ParentTask
{
    id : number;
    parentTaskDesc : string; 
    project : Project;
} 