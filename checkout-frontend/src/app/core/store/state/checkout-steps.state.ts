import { Injectable } from "@angular/core";
import { Action, Selector, State, StateContext } from "@ngxs/store";
import { SetCurrentStep, StartLoadingStep, StopLoadingStep } from "@core/store/actions/checkout-steps.actions";

export interface CheckoutSteps {
    currentStep: number;
    loadingStep: boolean;
    loadingStepMessage: string;
}

@State<CheckoutSteps>({
    name: 'checkoutSteps',
    defaults: {
        currentStep: 1,
        loadingStep: false,
        loadingStepMessage: ''
    }
})
@Injectable({
    providedIn: 'root'
})
export class CheckoutStepsState {

    @Action(SetCurrentStep)
    setCheckoutCurrentStep(ctx: StateContext<CheckoutSteps>, action: SetCurrentStep) {
        ctx.patchState({
            currentStep: action.step
        })
    }

    @Action(StartLoadingStep)
    startLoadingSteps(ctx: StateContext<CheckoutSteps>, action: StartLoadingStep) {
        const state = ctx.getState();
        ctx.patchState({
            loadingStep: true,
            loadingStepMessage: action.message
        })
    }

    @Action(StopLoadingStep)
    stopLoadingStep(ctx: StateContext<CheckoutSteps>) {
        ctx.patchState({
            loadingStep: false,
            loadingStepMessage: ''
        })
    }

    @Selector()
    static getCurrentStep(state: CheckoutSteps) {
        return state.currentStep;
    }

    @Selector()
    static getLoadingStep(state: CheckoutSteps) {
        return state.loadingStep;
    }

    @Selector()
    static getLoadingStepMessage(state: CheckoutSteps) {
        return state.loadingStepMessage;
    }
}