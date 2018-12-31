import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userFilter'
})
export class UserfilterPipe implements PipeTransform {

  transform(value: any, args?: any): any 
  {
    if (args!==undefined) {
      args = args.toLowerCase();
      return value.filter(user=>{
          return user.firstName.toLowerCase().startsWith(args)==true;
      })
  }
  return value;
  }

}
