import { inject, Injectable } from '@angular/core';
import { EcommerceApi } from './ecommerce.api';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private ecommerce = inject(EcommerceApi)

  getProducts() {
    return this.ecommerce.getProducts();
  }

}
