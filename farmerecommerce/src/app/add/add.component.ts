import { Component, OnInit } from '@angular/core';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import {FarmerService} from './../farmer.service';
@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  addForm:FormGroup|any;
  constructor(private fb:FormBuilder,private serv:FarmerService) { }
  ngOnInit(): void {
    this.addForm=this.fb.group({
      "sellerName":['',Validators.required],
      "productName":['',Validators.required],
      "productPrice":['',Validators.required]
    })
  }
  addProduct(){
    this.serv.addProduct(this.addForm.value).subscribe(
      (x)=>{
        console.log(x);
        alert("Product Updated in Database");
      },
      (y)=>{
        console.log(y);
        alert("Product Updated in Database");

      }
    )
  }
}
