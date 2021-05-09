import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerform: FormGroup|any;
  constructor(private fb:FormBuilder) { }

  ngOnInit(): void {
    this.registerform=this.fb.group({
      "FullName":['',[Validators.required]],
      "Email":['',[Validators.required]],
      "UserName":['',[Validators.required]],
      "Password":['',[Validators.required]]
      //bugs at pattern validation
    })
  }
  check(){
    console.log(this.registerform.value);
  }

}
