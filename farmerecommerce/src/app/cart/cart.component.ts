import { Component, OnInit } from '@angular/core';
import {cartDTO} from './cartDTO';
import {FarmerService} from './../farmer.service';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cart:cartDTO[]=[];
  sum:number=0;
  toggle:any=false;
  constructor(private serv:FarmerService) { }
  calculatePrice(){
    this.serv.getCartItems().subscribe(
      (product:cartDTO[]) =>{
        this.cart=product;
        this.cart.forEach((obj) => {
          this.sum=this.sum + parseInt(obj.price);
        })
        console.log(this.sum);
        console.log(this.cart);
        this.toggle=!this.toggle;
        alert("The Payable Amount is "+this.sum);
      },
      (error) => {
        console.log(error.error.text);
      }
    )
  }
  resetCart(){
    this.serv.getCartItems().subscribe(
      (product:cartDTO[]) =>{
        this.cart=product;
        this.cart.forEach((obj) => {
          obj.price=String(0);
        })
        console.log(this.cart);
      },
      (error) => {
        console.log(error.error.text);
      }
    )
  }
  ngOnInit(): void {
    this.serv.getCartItems().subscribe(
      (product:cartDTO[]) =>{
        this.cart=product;
        console.log(this.cart);
      },
      (error) => {
        console.log(error.error.text);
      }
    )
  }
}
