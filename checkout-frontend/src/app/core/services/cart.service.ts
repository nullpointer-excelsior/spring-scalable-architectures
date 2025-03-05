import { Injectable } from '@angular/core';
import { ProductModel } from '@core/models/product.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  calculateTotal(products: ProductModel[]) {
    return products.reduce((acc, item) => acc + item.price * item.quantity, 0)
  }

  calculateProductsQuantity(products: ProductModel[]) {
    return products.reduce((acc, item) => acc + item.quantity, 0)
  }

  updateProduct(product: ProductModel, products: ProductModel[]){
    return [
      ...products.filter(p => p.sku !== product.sku),
      product
    ]
  }

  deleteProduct(sku: number, products: ProductModel[]) {
    return products.filter(p => p.sku !== sku)
  }

}
