import { Injectable } from "@angular/core";
import { Action, Selector, State, StateContext } from "@ngxs/store";
import { CleanErrorStepAction, SetCurrentStepAction, SetErrorStepAction, StartLoadingStepAction, StopLoadingStepAction } from "@core/store/actions/checkout-steps.actions";

export interface CheckoutSteps {
    currentStep: number;
    loadingStep: boolean;
    loadingStepMessage: string;
    errorStep: boolean;
    errorStepMessage: string;
    
}

@State<CheckoutSteps>({
    name: 'checkoutSteps',
    defaults: {
        currentStep: 1,
        loadingStep: false,
        loadingStepMessage: '',
        errorStep: false,
        errorStepMessage: ''
    }
})
@Injectable({
    providedIn: 'root'
})
export class CheckoutStepsState {

    @Action(SetCurrentStepAction)
    setCheckoutCurrentStep(ctx: StateContext<CheckoutSteps>, action: SetCurrentStepAction) {
        ctx.dispatch(new StopLoadingStepAction())
        ctx.dispatch(new CleanErrorStepAction())
        ctx.patchState({
            currentStep: action.step
        })
    }

    @Action(StartLoadingStepAction)
    startLoadingSteps(ctx: StateContext<CheckoutSteps>, action: StartLoadingStepAction) {
        ctx.patchState({
            loadingStep: true,
            loadingStepMessage: action.message
        })
    }

    @Action(CleanErrorStepAction)
    clearErrorStepAction(ctx: StateContext<CheckoutSteps>, action: CleanErrorStepAction) {
        ctx.patchState({
            errorStep: false,
            errorStepMessage: ''
        })
    }

    @Action(StopLoadingStepAction)
    stopLoadingStep(ctx: StateContext<CheckoutSteps>) {
        ctx.patchState({
            loadingStep: false,
            loadingStepMessage: ''
        })
    }

    @Action(SetErrorStepAction)
    setErrorStep(ctx: StateContext<CheckoutSteps>, action: SetErrorStepAction){
        ctx.patchState({
            errorStep: true,
            errorStepMessage: action.message
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

    @Selector()
    static isErrorStep(state: CheckoutSteps) {
        return state.errorStep;
    }

    @Selector()
    static getErrorStepMessage(state: CheckoutSteps) {
        return state.errorStepMessage;
    }
}