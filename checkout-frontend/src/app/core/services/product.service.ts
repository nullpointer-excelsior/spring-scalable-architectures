import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { ProductModel } from '@core/models/product.model';
import { environment } from './../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private http = inject(HttpClient)

  getProducts() {
    return this.http.get<ProductModel[]>(`${environment.checkoutUrl}/products`);
  }

}
