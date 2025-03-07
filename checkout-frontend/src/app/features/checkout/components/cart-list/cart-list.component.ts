import { Component, inject, input } from '@angular/core';
import { ProductModel } from '@core/models/product.model';
import { Store } from '@ngxs/store';
import { DeleteCartProductAction, UpdateCartProductAction } from '@core/store/actions/cart.actions';

@Component({
  selector: 'app-cart-list',
  imports: [
  ],
  templateUrl: './cart-list.component.html'
})
export class CartListComponent {


  store = inject(Store)
  products = input<ProductModel[]>();

  deleteCartProduct(product: ProductModel) {
    this.store.dispatch(new DeleteCartProductAction(product.sku))
  }

  onProductQuantityChange(event: Event, product: ProductModel) {
    const newQuantity = Number((event.target as HTMLInputElement).value);
    if (newQuantity !== product.quantity) {
      product.quantity = newQuantity
      this.store.dispatch(new UpdateCartProductAction(product))
    }
  }

  onImageError(event: ErrorEvent) {
    (event.target as HTMLImageElement).src = '/img/product-default-img.webp';
  }

}
