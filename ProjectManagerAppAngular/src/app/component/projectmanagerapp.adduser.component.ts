import { Component } from '@angular/core';
import { UsersService} from '../service/usersservice'
import { UserfilterPipe } from '../userfilter.pipe';
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { OrderModule } from 'ngx-order-pipe';


@Component({
  selector: 'projectmanagerapp-displayusers',
  templateUrl: '../html/projectmanagerapp.adduser.component.html',
  styleUrls: ['../app.component.css'],
  providers: [UsersService]

})
export class AddUserComponent 
{
  data: any;
  orderByData:string='firstName';
  reverse : boolean = false;
  
  constructor(public usersService : UsersService,
    private _ngZone: NgZone,private router:Router)
  {
  }

  ngOnInit()
    {
      this.usersService.getAllUsers().subscribe(
        resp=>{this.data=resp},error=>{console.log(error,"error")}
      );
    }




    setOrder(event)
    {
      var buttonName= event.target.name;
        if(this.orderByData===buttonName)
        {
          
          this.reverse=!this.reverse;
        }
        else
        {
          this.orderByData=buttonName;
        }

      
    }

  addUser(addUserForm:NgForm)
  {

  }

  getAllWorkouts()
  {
 
  }

  deleteUser(userId:number)
  {
    if(confirm('Are you sure you want to delete this workout ? All past workout records would be deleted'))
    {
      this.router.navigate(['/workout/delete/'+userId]);
    }
  }



  
}
