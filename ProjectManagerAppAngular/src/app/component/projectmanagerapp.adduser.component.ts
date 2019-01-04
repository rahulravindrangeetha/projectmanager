import { Component } from '@angular/core';
import { UsersService} from '../service/usersservice'
import { UserfilterPipe } from '../userfilter.pipe';
import { NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { OrderModule } from 'ngx-order-pipe';
import { Users } from '../model/users.model';


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
  visible : boolean = false;
  user: Users=new Users();
  userId: number;
  firstName:string;
  lastName:string;
  employeeId:number;
  
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
    editUser(userToBeEdited:any)
    {
      this.visible=true;
      this.firstName=userToBeEdited.firstName;
      this.lastName=userToBeEdited.lastName;
      this.employeeId=userToBeEdited.employeeId;
      this.userId=userToBeEdited.userId;

    }

  addUser()
  {
    this.user = new Users();
      this.user.employeeId=this.employeeId;
      this.user.firstName=this.firstName;
      this.user.lastName=this.lastName;
      this.usersService.createNewUser(this.user).subscribe(
        resp=>{
          this.resetForm();
          this.ngOnInit();
        },
        error=>
        {console.log(error,"error")
      }
      );
  }

  resetForm()
  {
     this.firstName=null;
     this.lastName=null;
     this.employeeId=null;
  }

  updateUser()
  {
    this.user = new Users();
      this.user.employeeId=this.employeeId;
      this.user.firstName=this.firstName;
      this.user.lastName=this.lastName;
      this.user.userId=this.userId;
      this.usersService.updateUser(this.user).subscribe(
        resp=>{
          this.userId=null;
          this.visible=false;
          this.resetForm();
          this.ngOnInit();
        },
        error=>
        {console.log(error,"error")
      }
      );
  }

  deleteUser(userId:number)
  {
    if(confirm('Are you sure you want to delete this user ?'))
    {
      this.usersService.deleteUser(userId).subscribe(
        resp=>{
          this.userId=null;
          this.visible=false;
          this.resetForm();
          this.ngOnInit();
        },
        error=>
        {console.log(error,"error")
      }
      );
    }
  }



  
}
