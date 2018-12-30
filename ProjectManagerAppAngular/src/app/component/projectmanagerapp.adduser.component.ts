import { Component } from '@angular/core';
import { UsersService} from '../service/usersservice'
import { WorkoutfilterPipe } from '../workoutfilter.pipe'; 
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'workoutapp-displayworkouts',
  templateUrl: '../html/projectmanagerapp.adduser.component.html',
  styleUrls: ['../app.component.css'],
  providers: [UsersService]

})
export class AddUserComponent 
{
  data: any;
  
  constructor(public usersService : UsersService,
    private _ngZone: NgZone,private router:Router)
  {
  }

  addUser(addUserForm:NgForm)
  {

  }

  getAllWorkouts()
  {
    console.log('in getAllWorkouts ');
    try
    {
    this.workoutService.getAllWorkouts().
    subscribe(resp=>{this.data=resp},error=>{console.log(error,"error")});
    }
    catch(e)
    {
      console.log(e);
    }



  }

  deleteWorkout(workoutId:number)
  {
    if(confirm('Are you sure you want to delete this workout ? All past workout records would be deleted'))
    {
      this.router.navigate(['/workout/delete/'+workoutId]);
    }
  }



  
}
