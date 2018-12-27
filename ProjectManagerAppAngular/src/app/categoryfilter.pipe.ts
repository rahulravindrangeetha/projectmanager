import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'categoryfilter'
})
export class CategoryfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(workoutCategory=>{
          return workoutCategory.categoryName.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
