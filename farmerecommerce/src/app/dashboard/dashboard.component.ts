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
  val:number=0;
  x:number=0;
  y:number=0;
  z:number=0;
  tot:string="";
  pname:string="";
  quantForm:FormGroup|any;
  valcap:FormGroup|any;
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
  sendValueQty(Qty:string,pname:string,price:string){
    this.x=parseInt(Qty);
    this.y=parseInt(price);
    this.z= this.x * this.y;
    this.tot=String(this.z);
    this.valcap=this.fb.group({
      "productName":[pname,[Validators.required]],
      "price":[this.tot,[Validators.required]]
    })
    this.serv.makeCart(this.valcap.value).subscribe(
      (a) => {
        console.log(a);
        alert("Added to Cart");
      },
      (b) => {
        console.log(b.error.text);
        alert("Added to Cart");
      }
    )
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
  captureName(Pname:string){
    this.pname=Pname;
  }
  ngOnInit(): void {
    this.getUserType();
    this.viewProducts();
    this.quantForm=this.fb.group({
      "Quantity":[this.val,[Validators.required]]
    })
  }

}
