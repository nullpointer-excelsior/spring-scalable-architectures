import { Component, input, Input } from '@angular/core';
import { ItemCart } from '../../models/item-cart.model';

@Component({
  selector: 'app-cart-item',
  imports: [],
  templateUrl: './cart-item.component.html'
})
export class CartItemComponent {
  item = input<ItemCart>({
    sku: 0,
    name: '',
    brand: '',
    price: 0,
    quantity: 0,
    image: ''
  })
}
