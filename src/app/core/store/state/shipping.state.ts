import { Injectable } from '@angular/core';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { ShippingModel } from '@core/models/shipping.model';
import { SetDeliveryAction } from '@core/store/actions/set-delivery.action';
import { SetShippingAction } from '@core/store/actions/set-shipping.action';

@State<ShippingModel>({
    name: 'shipping',
    defaults: {
        delivery: 'free'
    }
})
@Injectable({
    providedIn: 'root'
})
export class ShippingState {

    @Action(SetShippingAction)
    setShipping(ctx: StateContext<ShippingModel>, action: SetShippingAction) {
        ctx.setState(action.shipping);
    }

    @Action(SetDeliveryAction)
    setDelivery(ctx: StateContext<ShippingModel>, action: SetDeliveryAction) {
        ctx.patchState({
            delivery: action.delivery
        });
    }

    @Selector()
    static getState(state: ShippingModel) {
        return state;
    }

    @Selector()
    static getDelivery(state: ShippingModel) {
        return state.delivery;
    }
}