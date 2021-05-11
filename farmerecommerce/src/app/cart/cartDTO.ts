export class cartDTO{
    productName:string;
	price:string;
    constructor(productName:string,price:string){
        this.productName=productName;
        this.price=price;
    }
}