import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup|any;
  constructor(private fb:FormBuilder) { }
  ngOnInit(): void {
    this.loginForm=this.fb.group({
      "UserName":['',[Validators.required]],
      "Password":['',[Validators.required]]
    })
  }
  check(){
    console.log(this.loginForm.value);
  }
}
