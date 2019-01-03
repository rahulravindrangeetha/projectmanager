import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'parentTaskFilter'
})
export class ParentTaskfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(parentTask=>{
          return parentTask.parentTaskDesc.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
