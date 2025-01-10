import { Injectable } from '@angular/core';
import { CartModel } from '@core/models/cart.model';
import { ProductModel } from '@core/models/product.model';
import { SetCartAction } from '@core/store/actions/set-cart.action';
import { UpdateCheckoutProductsAction as UpdateCartProductsAction } from '@core/store/actions/update-checkout-products.action';
import { Action, Selector, State, StateContext } from '@ngxs/store';

@State<CartModel>({
  name: 'cart',
  defaults: {
    products: [],
    total: 0
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

  @Action(UpdateCartProductsAction)
  updateCheckoutProducts(ctx: StateContext<CartModel>, { products }: UpdateCartProductsAction) {
    const state = ctx.getState();
    ctx.setState({
      ...state,
      products: products,
      total: products.reduce((acc, item) => acc + item.price * item.quantity, 0)
    });
  }

  @Selector()
  static getProducts(state: CartModel): ProductModel[] {
    return state.products;
  }

  @Selector()
  static getTotal(state: CartModel): number {
    return state.total;
  }

  @Selector()
  static getTotalProducts(state: CartModel) {
    return state.products.reduce((acc, item) => acc + item.quantity, 0)
  }
}
