import { Component, input, Input, OnInit } from '@angular/core';
import { ItemCartModel } from '@core/models/cart.model';

@Component({
  selector: 'app-cart-list',
  imports: [
  ],
  templateUrl: './cart-list.component.html'
})
export class CartListComponent{
  
  items = input<ItemCartModel[]>();

}
