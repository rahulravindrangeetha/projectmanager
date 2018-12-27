import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'workoutfilter'
})
export class WorkoutfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(workout=>{
          return workout.workoutTitle.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
