import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'projectFilter'
})
export class ProjectfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(projects=>{
          return projects.project.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
