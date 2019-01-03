import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'taskFilter'
})
export class TaskfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(tasks=>{
          return tasks.task.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
