import { Component } from '@angular/core';
import { BorderIndicatorDirective } from '../../directives/border-indicator.directive';
import { ItemCart } from '../../models/item-cart.model';
import { CartListComponent } from "../cart-list/cart-list.component";
import { CheckoutButtonDirective } from '../../directives/checkout-button.directive';
import { ConfirmationItemSummaryComponent } from "../confirmation-item-summary/confirmation-item-summary.component";

@Component({
  selector: 'app-confirmation',
  imports: [CartListComponent, BorderIndicatorDirective, CheckoutButtonDirective, ConfirmationItemSummaryComponent],
  templateUrl: './confirmation.component.html'
})
export class ConfirmationComponent {

  cartItems: ItemCart[] = [{
    sku: 1,
    name: "Judas Priest T-Shirt",
    brand: "Generic",
    price: 20000,
    quantity: 1,
    image: "/img/p1.webp"
  },
  {
    sku: 2,
    name: "Beer six pack",
    brand: "Becker",
    price: 7000,
    quantity: 4,
    image: "/img/p2.webp"
  },
  {
    sku: 3,
    name: "Guitar strings",
    brand: "Ernie ball",
    price: 13000,
    quantity: 1,
    image: "/img/p3.webp"
  },
  {
    sku: 4,
    name: "Marshall Amp 50W",
    brand: "Marshall",
    price: 400000,
    quantity: 1,
    image: "/img/p4.webp"
  },
  {
    sku: 5,
    name: "Warlock Guitar",
    brand: "BC Rich",
    price: 700000,
    quantity: 1,
    image: "/img/p5.webp"
  }]; 


}
