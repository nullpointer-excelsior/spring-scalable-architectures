import { Component, inject, input } from '@angular/core';
import { ProductModel } from '@core/models/product.model';
import { DeleteCartProductAction } from '@core/store/actions/delete-cart-product.action';
import { Store } from '@ngxs/store';

@Component({
  selector: 'app-cart-list',
  imports: [
  ],
  templateUrl: './cart-list.component.html'
})
export class CartListComponent {
  
  store = inject(Store)
  products = input<ProductModel[]>();

  deleteCartProductAction(product: ProductModel) {
   this.store.dispatch(new DeleteCartProductAction(product.sku))
  }

}
