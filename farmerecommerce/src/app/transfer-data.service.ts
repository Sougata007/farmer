import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TransferDataService {
  private data:any = {};
  constructor() { }
  
  setOption(option:any,value:any){
    this.data[option]=value;
  }
  getOption(){
    return this.data;
  }
}
