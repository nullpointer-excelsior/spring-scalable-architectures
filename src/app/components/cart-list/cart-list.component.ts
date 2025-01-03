import { Component, input, Input, OnInit } from '@angular/core';
import { CartItemComponent } from '../cart-item/cart-item.component';
import { ItemCart } from '../../models/item-cart.model';

@Component({
  selector: 'app-cart-list',
  imports: [
    CartItemComponent
  ],
  templateUrl: './cart-list.component.html',
  styles: ``
})
export class CartListComponent{
  
  items = input<ItemCart[]>();

}
