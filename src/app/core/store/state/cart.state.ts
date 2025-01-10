import { Injectable } from '@angular/core';
import { CartModel } from '@core/models/cart.model';
import { ProductModel } from '@core/models/product.model';
import { SetCartAction } from '@core/store/actions/set-cart.action';
import { UpdateCheckoutProductsAction as UpdateCartProductsAction } from '@core/store/actions/update-checkout-products.action';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { DeleteCartProductAction } from '@core/store/actions/delete-cart-product.action';
import { CartService } from '@core/services/cart.service';
import { UpdateCartProductAction } from '@core/store/actions/update-cart-product.action';

@State<CartModel>({
  name: 'cart',
  defaults: {
    products: [],
    amount: 0,
    productsQuantity: 0
  }
})
@Injectable({
  providedIn: 'root'
})
export class CartState {

  constructor(private cart: CartService) {}

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
      amount: this.cart.calculateTotal(products),
      productsQuantity: this.cart.calculateProductsQuantity(products)
    });
  }

  @Action(UpdateCartProductAction)
  updateCartProductAction(ctx: StateContext<CartModel>, { product }: UpdateCartProductAction) {
    const state = ctx.getState();
    const products = this.cart.updateProduct(product, state.products)
    ctx.dispatch(new UpdateCartProductsAction(products))
  }

  @Action(DeleteCartProductAction)
  deleteCartProductAction(ctx: StateContext<CartModel>, { sku }: DeleteCartProductAction) {
    const state = ctx.getState();
    const products = this.cart.deleteProduct(sku, state.products)
    ctx.patchState({
      products,
      amount: this.cart.calculateTotal(products),
      productsQuantity: this.cart.calculateProductsQuantity(products)
    })
  }

  @Selector()
  static getProducts(state: CartModel): ProductModel[] {
    return state.products.sort((a, b) => +a.sku - +b.sku);
  }

  @Selector()
  static getAmount(state: CartModel): number {
    return state.amount;
  }

  @Selector()
  static getProductQuantity(state: CartModel) {
    return state.productsQuantity
  }
}
