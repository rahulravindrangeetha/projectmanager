import { WorkoutCategory } from "./workoutcategory.model";
export class WorkoutCollection
{
    workoutId : number;
    workoutNote: string;
    workoutTitle : string;
    calBurnPerMin : number;
    workoutCategory : WorkoutCategory;
}