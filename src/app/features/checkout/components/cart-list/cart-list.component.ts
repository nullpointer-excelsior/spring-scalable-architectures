import { Component, input } from '@angular/core';
import { ProductModel } from '@core/models/product.model';

@Component({
  selector: 'app-cart-list',
  imports: [
  ],
  templateUrl: './cart-list.component.html'
})
export class CartListComponent{
  
  products = input<ProductModel[]>();

}
