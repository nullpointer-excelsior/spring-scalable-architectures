import { PaymentMethod } from "@core/models/billing.model";
import { CheckoutModel } from "@core/models/checkout.model";
import { Delivery } from "@core/models/shipping.model";
import { SetBillingAction } from "@core/store/actions/set-billing.action";
import { SetContactInfoAction } from "@core/store/actions/set-contact-info.action";
import { SetShippingAction } from "@core/store/actions/set-shipping.action";
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

    @Selector()
    static getShippingDelivery(state: CheckoutModel) {
        return state.shipping.delivery;
    }
}