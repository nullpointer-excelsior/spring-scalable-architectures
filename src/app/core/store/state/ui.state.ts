import { Injectable } from "@angular/core";
import { Action, Selector, State } from "@ngxs/store";
import { SetCurrentStep as SetCheckoutCurrentStep } from "@core/store/actions/set-current-step.action";

export interface UI {
    checkoutSteps: {
        currentStep: number;
    }
}

@State<UI>({
    name: 'ui',
    defaults: {
        checkoutSteps: {
            currentStep: 1
        }
    }
})
@Injectable({
    providedIn: 'root'
})
export class UIState {

    @Action(SetCheckoutCurrentStep)
    setCheckoutCurrentStep(ctx: any, action: SetCheckoutCurrentStep) {
        const state = ctx.getState();
        ctx.setState({
            ...state,
            checkoutSteps: {
                currentStep: action.step
            }
        });
    }

    @Selector()
    static getCheckoutCurrentStep(state: UI) {
        return state.checkoutSteps.currentStep;
    }
}