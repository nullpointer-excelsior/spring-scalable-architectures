import { Injectable } from '@angular/core';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { BillingModel } from '@core/models/billing.model';
import { SetPaymentMethodAction } from '@core/store/actions/set-payment-method.action';
import { SetBillingAction } from '@core/store/actions/set-billing.action';
import { SetContactInfoAction } from '@core/store/actions/set-contact-info.action';

@State<BillingModel>({
    name: 'billing',
    defaults: {
        contactInfo: {
            fullname: '',
            address: '',
            city: '',
        },
        paymentMethod: {
            type: '',
            payment: null
        }
    }
})
@Injectable({
    providedIn: 'root'
})
export class BillingState {

    @Action(SetBillingAction)
    setBilling(ctx: StateContext<BillingModel>, action: SetBillingAction) {
        ctx.setState(action.billing);
    }

    @Action(SetPaymentMethodAction)
    setPaymentMethod(ctx: StateContext<BillingModel>, action: SetPaymentMethodAction) {
        const state = ctx.getState();
        ctx.setState({
            ...state,
            paymentMethod: action.paymentMethod
        });
    }

    @Action(SetContactInfoAction)
    setContactInfo(ctx: StateContext<BillingModel>, action: SetContactInfoAction) {
        const state = ctx.getState();
        ctx.setState({
            ...state,
            contactInfo: action.contactInfo
        });
    }

    @Selector()
    static getState(state: BillingModel) {
        return state;
    }

    @Selector()
    static getContactInfo(state: BillingModel) {
        return state.contactInfo;
    }
}