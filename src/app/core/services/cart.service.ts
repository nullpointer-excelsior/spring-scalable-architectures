import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { CartModel, ItemCartModel } from '../models/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: ItemCartModel[] = [{
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
  

    createRandomCart(limit: number): Observable<CartModel> {
      const items = this.cartItems.sort(() => .5 - Math.random()).slice(0, limit);
      return of({
        items
      })
    }

}
