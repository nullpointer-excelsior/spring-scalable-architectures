import { inject, Injectable } from "@angular/core";
import { PaymentMethod } from "@core/models/billing.model";
import { CartModel } from "@core/models/cart.model";
import { CheckoutModel } from "@core/models/checkout.model";
import { Delivery } from "@core/models/shipping.model";
import { EcommerceApi } from "@core/services/ecommerce.api";
import { ProductService } from "@core/services/product.service";
import { CreateCartAction } from "@core/store/actions/cart.actions";
import { CreateRandomCheckoutAction, SetBillingAction, SetContactInfoAction, SetShippingAction } from "@core/store/actions/checkout.actions";
import { SetUserAction } from "@core/store/actions/user.actions";
import { CartState } from "@core/store/state/cart.state";
import { getRandomElements } from "@core/utils/get-random-elements";
import { getUsers } from "@core/utils/get-users";
import { Action, Selector, State, StateContext } from "@ngxs/store";
import { map, tap } from "rxjs";

@State<CheckoutModel>({
    name: 'checkout',
    defaults: {
        billing: {
            contactInfo: {
                dni: '12345678-5',
                fullname: 'juan perez',
            },
            payment: {
                methodStatus: "unchecked",
                method: PaymentMethod.CreditCard,
                details: {
                    cardName: 'j card',
                    cardNumber: '1234',
                    expiration: '2/27',
                    cvv: 100
                }
            }
        },
        shipping: {
            delivery: Delivery.FREE,
            fullname: '',
            address: '',
            city: ''
        }
    }
})
@Injectable()
export class CheckoutState {

    private productService = inject(ProductService);
    private ecommerce = inject(EcommerceApi);

    @Action(SetBillingAction)
    setBilling(ctx: StateContext<CheckoutModel>, action: SetBillingAction) {
        return this.ecommerce.validatePaymentMethod({
            method: action.billing.payment.method,
            details: {
                ...action.billing.payment.details
            }
        }).pipe(
            map(res => res.valid ? 'accepted' : 'failed'),
            tap(methodStatus => {
                ctx.patchState({
                    billing: {
                        ...action.billing,
                        payment: {
                            ...action.billing.payment,
                            methodStatus
                        },
                    }
                });

            })
        )

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
                delivery: Delivery.FREE,
                fullname: user.fullname,
                address: user.address.street,
                city: user.address.city
            }
        })
        const quantityLimit = 15
        return this.productService.getProducts().pipe(
            map(products => products.filter(product => product.quantity >= quantityLimit)),
            map(products => products.map(product => ({
                ...product,
                quantity: Math.floor(Math.random() * quantityLimit) + 1
            }))),
            map(products => getRandomElements(products, 7)),
            tap(cart => {
                ctx.dispatch([
                    new SetUserAction(user),
                    new CreateCartAction({
                        products: cart,
                        user,
                    })
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
            shippingOption: checkout.shipping.delivery === Delivery.FREE ? 'Regular (3 -7 days)' : 'Express (1 -3 days)',
            paymentMethod: checkout.billing.payment.method,
            productsQuantity: cart.productsQuantity,
            amount: cart.amount
        }
    }

    @Selector()
    static getPayment(state: CheckoutModel) {
        return state.billing.payment
    }

    @Selector()
    static getPaymentMethodStatus(state: CheckoutModel) {
        return state.billing.payment.methodStatus
    }
}