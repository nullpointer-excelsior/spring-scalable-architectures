import { PaymentMethod } from "@core/models/billing.model";
import { CartModel } from "@core/models/cart.model";
import { CheckoutModel } from "@core/models/checkout.model";
import { Delivery } from "@core/models/shipping.model";
import { CreateRandomCheckoutAction } from "@core/store/actions/create-random-checkout.action";
import { SetBillingAction } from "@core/store/actions/set-billing.action";
import { SetContactInfoAction } from "@core/store/actions/set-contact-info.action";
import { SetShippingAction } from "@core/store/actions/set-shipping.action";
import { SetUserAction } from "@core/store/actions/set-user.action";
import { UpdateCheckoutProductsAction } from "@core/store/actions/update-checkout-products.action";
import { CartState } from "@core/store/state/cart.state";
import { getCartProducts } from "@core/utils/get-cart-items";
import { getRandomElements } from "@core/utils/get-random-elements";
import { getUsers } from "@core/utils/get-users";
import { Action, Selector, State, StateContext } from "@ngxs/store";

@State<CheckoutModel>({
    name: 'checkout',
    defaults: {
        billing: {
            contactInfo: {
                fullname: '',
                address: '',
                city: ''
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
        }
    }
})
export class CheckoutState {

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
        const products = getRandomElements(getCartProducts(), 3);
        ctx.patchState({
            billing: {
                ...state.billing,
                contactInfo: {
                    fullname: user.fullname,
                    address: user.address.street,
                    city: user.address.city
                }
            }
        })
        ctx.dispatch([
            new SetUserAction(user),
            new UpdateCheckoutProductsAction(products)
        ])
    }

    @Selector()
    static getShippingDelivery(state: CheckoutModel) {
        return state.shipping.delivery;
    }

    @Selector()
    static getState(state: CheckoutModel) {
        return state
    }

    @Selector()
    static getBillingForm(state: CheckoutModel) {
        const { billing } = state
        const { fullname, address, city } = billing.contactInfo
        const { cardName, cardNumber, expiration, cvv } = billing.payment.details
        return {
            fullname,
            address,
            city,
            cardName,
            cardNumber,
            expiration,
            cvv,
        }
    }

    @Selector([CheckoutState, CartState])
    static getCheckoutSummary(checkout: CheckoutModel, cart: CartModel) {
        return {
            shippingOption: checkout.shipping.delivery === Delivery.Standard? 'Regular (3 -7 days)' : 'Express (1 -3 days)',
            paymentMethod: checkout.billing.payment.method,
            productsQuantity: cart.productsQuantity,
            amount: cart.amount
        }
    }
}