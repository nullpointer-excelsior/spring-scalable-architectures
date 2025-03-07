import { inject, Injectable } from "@angular/core";
import { PaymentMethod } from "@core/models/billing.model";
import { CartModel } from "@core/models/cart.model";
import { CheckoutModel } from "@core/models/checkout.model";
import { Delivery } from "@core/models/shipping.model";
import { UpdateCartProductsAction } from "@core/store/actions/cart.actions";
import { CreateRandomCheckoutAction, SetBillingAction, SetContactInfoAction, SetShippingAction } from "@core/store/actions/checkout.actions";
import { SetUserAction } from "@core/store/actions/user.actions";
import { CartState } from "@core/store/state/cart.state";
import { getRandomElements } from "@core/utils/get-random-elements";
import { getUsers } from "@core/utils/get-users";
import { Action, Selector, State, StateContext } from "@ngxs/store";
import { ProductService } from "@core/services/product.service";
import { map, tap } from "rxjs";

@State<CheckoutModel>({
    name: 'checkout',
    defaults: {
        billing: {
            contactInfo: {
                dni: '',
                fullname: '',
            },
            payment: {
                method: PaymentMethod.CreditCard,
                details: {
                    cardName: '',
                    cardNumber: '',
                    expiration: '',
                    cvv: 0
                }
            }
        },
        shipping: {
            delivery: Delivery.Standard,
            fullname: '',
            address: '',
            city: ''
        }
    }
})
@Injectable()
export class CheckoutState {

    private productService = inject(ProductService);

    @Action(SetBillingAction)
    setBilling(ctx: StateContext<CheckoutModel>, action: SetBillingAction) {
        ctx.setState({
            ...ctx.getState(),
            billing: action.billing
        });
    }

    @Action(SetContactInfoAction)
    setContactInfo(ctx: StateContext<CheckoutModel>, action: SetContactInfoAction) {
        const state = ctx.getState();
        ctx.setState({
            ...state,
            billing: {
                ...state.billing,
                contactInfo: action.contactInfo
            }
        });
    }

    @Action(SetShippingAction)
    setShipping(ctx: StateContext<CheckoutModel>, action: SetShippingAction) {
        ctx.setState({
            ...ctx.getState(),
            shipping: action.shipping
        });
    }

    @Action(CreateRandomCheckoutAction)
    createRandomChekout(ctx: StateContext<CheckoutModel>) {
        const state = ctx.getState();
        const user = getRandomElements(getUsers(), 1)[0];
        ctx.patchState({
            billing: {
                ...state.billing,
                contactInfo: {
                    dni: '12345678-5',
                    fullname: user.fullname,
                }
            },
            shipping: {
                delivery: Delivery.Standard,
                fullname: user.fullname,
                address: user.address.street,
                city: user.address.city
            }
        })
        return this.productService.getProducts().pipe(
            map(products => getRandomElements(products, 7)),
            tap(products => {
                ctx.dispatch([
                    new SetUserAction(user),
                    new UpdateCartProductsAction(products)
                ])
            })
        )
    }

    @Selector()
    static getShipping(state: CheckoutModel) {
        return state.shipping;
    }

    @Selector()
    static getState(state: CheckoutModel) {
        return state
    }

    @Selector()
    static getBillingForm(state: CheckoutModel) {
        const { billing } = state
        const { fullname, dni } = billing.contactInfo
        const { cardName, cardNumber, expiration, cvv } = billing.payment.details
        return {
            dni,
            fullname,
            cardName,
            cardNumber,
            expiration,
            cvv,
        }
    }

    @Selector([CheckoutState, CartState])
    static getCheckoutSummary(checkout: CheckoutModel, cart: CartModel) {
        return {
            shippingOption: checkout.shipping.delivery === Delivery.Standard ? 'Regular (3 -7 days)' : 'Express (1 -3 days)',
            paymentMethod: checkout.billing.payment.method,
            productsQuantity: cart.productsQuantity,
            amount: cart.amount
        }
    }
}