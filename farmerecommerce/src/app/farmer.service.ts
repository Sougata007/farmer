import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {productDTO} from './dashboard/productDTO';
import {cartDTO} from './cart/cartDTO';
@Injectable({
  providedIn: 'root'
})
export class FarmerService {

  constructor(private http:HttpClient) { }

  register(userData:any):Observable<any>{
    return this.http.post('http://localhost:8765/infybank/user',userData);
  }
  login(username:string){
    return this.http.get('http://localhost:8765/infybank/user/'+username);
  }
  getUserType(username:string){
    return this.http.get('http://localhost:8765/infybank/'+username);
  }
  getAllProducts():Observable<productDTO[]>{
    return this.http.get<productDTO[]>('http://localhost:8765/infybank/products');
  }
  makeCart(cartData:any):Observable<any>{
    return this.http.post('http://localhost:8765/infybank/cart',cartData);
  }
  getCartItems():Observable<cartDTO[]>{
    return this.http.get<cartDTO[]>('http://localhost:8765/infybank/carts');
  }
  addProduct(productData:any):Observable<any>{
    return this.http.post('http://localhost:8765/infybank/product',productData);
  }
  deleteProduct(sellerName:string){
    return this.http.delete('http://localhost:8765/infybank/product/'+sellerName);
  }
}
