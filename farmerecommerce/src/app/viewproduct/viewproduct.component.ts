import { Component, OnInit } from '@angular/core';
import {productDTO} from '../dashboard/productDTO';
import {FarmerService} from '../farmer.service';
@Component({
  selector: 'app-viewproduct',
  templateUrl: './viewproduct.component.html',
  styleUrls: ['./viewproduct.component.css']
})
export class ViewproductComponent implements OnInit {
  product:productDTO[]=[];
  constructor(private serv:FarmerService) { }

  deleteProduct(sellerName:string){
    this.serv.deleteProduct(sellerName).subscribe(
      (x)=>{
        console.log("deleted");
      },
      (y)=>{
        console.log(y.error.text);
      }
    )
  }
  ngOnInit(): void {
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
}
