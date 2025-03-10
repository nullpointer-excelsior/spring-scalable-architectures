import { inject, Injectable } from '@angular/core';
import { ProductModel } from '@core/models/product.model';
import { EcommerceApi } from '@core/services/ecommerce.api';
import { CartResponse, CreateCartRequest } from '@core/dto/ecommerce.dto';
import { map, Observable, switchMap } from 'rxjs';
import { CartModel } from '@core/models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private ecommerce = inject(EcommerceApi)

  createCart(dto: CreateCartRequest): Observable<CartModel> {
    return this.ecommerce.createCart(dto).pipe(
      map(cart => this.transformCart(cart))
    )
  }

  updateProductCart(cartId: number, product: ProductModel): Observable<CartModel> {
    return this.ecommerce.updateProductCart(cartId, product).pipe(
      switchMap(() => this.ecommerce.getCartById(cartId)),
      map(cart => this.transformCart(cart))
    )
  }

  deleteProductCart(cartId: number, product: ProductModel): Observable<CartModel> {
    return this.ecommerce.deleteProductCart(cartId, product).pipe(
      switchMap(() => this.ecommerce.getCartById(cartId)),
      map(cart => this.transformCart(cart))
    )
  }

  private transformCart(cart: CartResponse): CartModel {
    const products = cart.products.sort((a, b) => +a.sku - +b.sku)
    return {
      id: cart.id,
      products,
      amount: this.calculateAmount(products),
      productsQuantity: this.calculateProductsQuantity(products)
    }
  }

  private calculateAmount(products: ProductModel[]) {
    return products.reduce((acc, item) => acc + item.price * item.quantity, 0)
  }

  private calculateProductsQuantity(products: ProductModel[]) {
    return products.reduce((acc, item) => acc + item.quantity, 0)
  }

}
