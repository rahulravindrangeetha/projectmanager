import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'noTaskFilter'
})
export class NoTaskfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {

      return value.filter(project=>{
          return project.noOfTasks !=0;
      })

  return value;
  }

}
