import { inject, Injectable } from '@angular/core';
import { CartModel } from '@core/models/cart.model';
import { ProductModel } from '@core/models/product.model';
import { CartService } from '@core/services/cart.service';
import { CreateCartAction, DeleteCartProductAction, UpdateCartProductAction } from '@core/store/actions/cart.actions';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { tap } from 'rxjs';

@State<CartModel>({
  name: 'cart',
  defaults: {
    id: null,
    products: [],
    amount: 0,
    productsQuantity: 0
  }
})
@Injectable({
  providedIn: 'root'
})
export class CartState {

  private cart = inject(CartService)

  @Action(CreateCartAction)
  createCart(ctx: StateContext<CartModel>, action: CreateCartAction) {
    return this.cart.createCart({ ...action.cart }).pipe(
      tap(cart => ctx.setState(cart))
    )
  }

  @Action(UpdateCartProductAction)
  updateCartProductAction(ctx: StateContext<CartModel>, { product }: UpdateCartProductAction) {
    const state = ctx.getState();
    return this.cart.updateProductCart(state.id, product).pipe(
      tap(cart => ctx.setState(cart))
    )
  }

  @Action(DeleteCartProductAction)
  deleteCartProductAction(ctx: StateContext<CartModel>, action: DeleteCartProductAction) {
    const state = ctx.getState();
    return this.cart.deleteProductCart(state.id, action.product).pipe(
      tap(cart => ctx.setState(cart))
    );
  }

  @Selector()
  static getProducts(state: CartModel): ProductModel[] {
    return state.products;
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
