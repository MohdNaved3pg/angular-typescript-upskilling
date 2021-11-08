import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../model/product';
import { environment } from 'src/environments/environment';
@Injectable()
export class ProductService {
    private endpointUrl = environment.backendURL;
    constructor(private http: HttpClient) { }
    getProducts(categoryId:string): Observable<Product[]> {
        let productEndpoint : string = categoryId === null
                                ? "/products"
                                : "/products/category/" + categoryId;
        return this.http.get<Product[]>(this.endpointUrl + productEndpoint );
    }
    getProduct(productId: string): Observable<Product> {
        return this.http.get<Product>(this.endpointUrl+"/product/"+productId);
    }
}
