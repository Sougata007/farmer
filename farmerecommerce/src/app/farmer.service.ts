import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FarmerService {

  constructor(private http:HttpClient) { }

  register(userData:any):Observable<any>{
    return this.http.post('http://localhost:8765/infybank/user',userData);
  }
}
