import { Injectable } from '@angular/core';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { CartModel, ItemCartModel } from '@core/models/cart.model';
import { SetCartAction } from '@core/store/actions/set-cart.action';

@State<CartModel>({
  name: 'cart',
  defaults: {
    items: []
  }
})
@Injectable({
  providedIn: 'root'
})
export class CartState {

  @Action(SetCartAction)
  setCart(ctx: StateContext<CartModel>, action: SetCartAction) {
    ctx.setState(action.cart);
  }
  
  @Selector()
  static getItems(state: CartModel): ItemCartModel[] {
    return state.items;
  }

  @Selector()
  static getTotal(state: CartModel): number {
    return state.items.reduce((acc, item) => acc + item.price * item.quantity, 0);
  }
}
