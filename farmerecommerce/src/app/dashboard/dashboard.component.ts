import { Component, OnInit } from '@angular/core';
import {TransferDataService} from '../transfer-data.service';
import {FormBuilder,FormGroup,Validators} from '@angular/forms';
import {FarmerService} from '../farmer.service';
import {productDTO} from './productDTO';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  public data:any;
  public data1:any;
  quantForm:FormGroup|any;
  product:productDTO[]=[];
  constructor(private trans:TransferDataService,private serv:FarmerService,private fb:FormBuilder) {
    this.data = trans.getOption();
    console.log(this.data.username);
  }
  getUserType(){
    this.serv.getUserType(this.data.username).subscribe(
      (x) => {
        console.log(x);
      },
      (y) => {
        this.data1=y.error.text;
        console.log(y.error.text);
      }
    )
  }
  sendValueQty(Qty:string){
    parseInt(Qty);
    console.log(Qty);
  }
  viewProducts(){
    this.serv.getAllProducts().subscribe(
      (product:productDTO[]) =>{
        this.product=product;
        console.log(product);
      },
      (error) => {
        console.log(error.error.text);
      }
    )
  }
  ngOnInit(): void {
    this.getUserType();
    this.viewProducts();
    this.quantForm=this.fb.group({
      "Quantity":['',[Validators.required]]
    })
  }

}
