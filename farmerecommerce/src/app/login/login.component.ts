import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import {FarmerService} from '../farmer.service';
import {TransferDataService} from '../transfer-data.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup|any;
  message:any;
  passcheck:any=false;
  constructor(private fb:FormBuilder,private serv:FarmerService,private trans:TransferDataService) { }
  ngOnInit(): void {
    this.loginForm=this.fb.group({
      "username":['',[Validators.required]],
      "password":['',[Validators.required]]
    })
  }
  check(){
    console.log(this.loginForm.value);
    this.message=null;
    this.serv.login(this.loginForm.value.username).subscribe(
      (x) => {
          this.message=x;
          if(this.loginForm.value.password == this.message)
        {
          this.passcheck=true;
          console.log(this.passcheck);
          this.trans.setOption('username',this.loginForm.value.username);
        }
        else
        {
          this.passcheck="Not Match";
          alert("Please Enter Correct Password");
        }
      },
      (y)=>{
        this.message=y.error.text;
        if(this.loginForm.value.password == this.message)
        {
          this.passcheck=true;
          console.log(this.passcheck);
          this.trans.setOption('username',this.loginForm.value.username)
        }
        else
        {
          this.passcheck="Not Match";
          alert("Please Enter Correct Password");
        }
      }
    )
  }
}
