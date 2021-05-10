import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import {FarmerService} from '../farmer.service';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerform: FormGroup|any;
  constructor(private fb:FormBuilder,private serv:FarmerService) { }
  toggle:any=false;
  ngOnInit(): void {
    this.registerform=this.fb.group({
      "fullname":['',[Validators.required]],
      "emailId":['',[Validators.required]],
      "username":['',[Validators.required]],
      "userType":['',[Validators.required]],
      "password":['',[Validators.required]]
      //bugs at pattern validation
    })
  }
  check(){
    this.serv.register(this.registerform.value).subscribe(
      x => {
        alert(this.registerform.value.username+" is successfully registered.Login Now");
        this.toggle=true;
      },
      y => {
        alert(this.registerform.value.username+" is successfully registered.Login Now");
        this.toggle=true;
      }
    )
  }

}
