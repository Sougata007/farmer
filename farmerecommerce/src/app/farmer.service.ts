import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {productDTO} from './dashboard/productDTO';
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
}
