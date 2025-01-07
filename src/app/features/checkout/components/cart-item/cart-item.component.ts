import { Component, input, Input } from '@angular/core';
import { ItemCartModel } from '@core/models/cart.model';

@Component({
  selector: 'app-cart-item',
  imports: [],
  templateUrl: './cart-item.component.html'
})
export class CartItemComponent {
  item = input<ItemCartModel>({
    sku: 0,
    name: '',
    brand: '',
    price: 0,
    quantity: 0,
    image: ''
  })
}
