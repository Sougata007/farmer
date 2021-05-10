export class productDTO{
    sellerName:string;
	productName:string;
	productPrice:string;
    constructor(sellerName:string,productName:string,productPrice:string){
        this.sellerName=sellerName;
        this.productName=productName;
        this.productPrice=productPrice;
    }
}