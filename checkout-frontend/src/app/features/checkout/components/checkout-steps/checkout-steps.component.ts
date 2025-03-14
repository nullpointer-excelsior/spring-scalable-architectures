import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Actions, ofActionCompleted, ofActionDispatched, ofActionErrored, ofActionSuccessful, Store } from '@ngxs/store';
import { StepIndicatorComponent } from "@features/checkout/components/step-indicator/step-indicator.component";
import { CheckoutStepsState } from '@core/store/state/checkout-steps.state';
import { CreatePurchaseAction, CreateRandomCheckoutAction, SetBillingAction, SetShippingAction } from '@core/store/actions/checkout.actions';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Router } from '@angular/router';
import { map, mergeMap, take, tap } from 'rxjs';
import { SetCurrentStepAction, SetErrorStepAction, StartLoadingStepAction, StopLoadingStepAction } from '@core/store/actions/checkout-steps.actions';
import { ToastrService } from 'ngx-toastr';
import { CheckoutState } from '@core/store/state/checkout.state';

@UntilDestroy()
@Component({
  selector: 'app-checkout-steps',
  imports: [
    CommonModule,
    StepIndicatorComponent
  ],
  templateUrl: './checkout-steps.component.html'
})
export class CheckoutStepsComponent implements OnInit {
  private store = inject(Store);
  private actions$ = inject(Actions);
  private router = inject(Router);
  private toastr = inject(ToastrService)

  currentStep = this.store.selectSignal(CheckoutStepsState.getCurrentStep);
  loadingStep = this.store.selectSignal(CheckoutStepsState.getLoadingStep);
  loadingStepMessage = this.store.selectSignal(CheckoutStepsState.getLoadingStepMessage);
  isErrorStep = this.store.selectSignal(CheckoutStepsState.isErrorStep);
  errorStepMessage = this.store.selectSignal(CheckoutStepsState.getErrorStepMessage);

  ngOnInit(): void {
    this.store.dispatch(new CreateRandomCheckoutAction())
    this.store.dispatch(new SetCurrentStepAction(1))
    // succesfully shipping step flow
    this.actions$
      .pipe(
        ofActionCompleted(SetShippingAction),
        untilDestroyed(this)
      )
      .subscribe(() => {
        this.store.dispatch(new SetCurrentStepAction(2))
        this.router.navigate(['/billing'])
      })
    // succesfully billing step flow
    this.actions$
      .pipe(
        ofActionDispatched(SetBillingAction),
        tap(() => this.store.dispatch(new StartLoadingStepAction('Validating your payment method...'))),
        mergeMap(() => this.actions$.pipe(
          ofActionSuccessful(SetBillingAction),
          take(1)
        )),
        untilDestroyed(this),
        map(() => this.store.selectSnapshot(CheckoutState.getPaymentMethodStatus))
      )
      .subscribe(status => {
        if (status === 'accepted') {
          this.store.dispatch(new SetCurrentStepAction(3))
          this.router.navigate(['/confirmation'])
        } else {
          this.toastr.error('Payment method not valid')
        }
      })
    // succesfully purchase step flow
    this.actions$
      .pipe(
        ofActionDispatched(CreatePurchaseAction),
        tap(() => this.store.dispatch(new StartLoadingStepAction('Processing your payment...'))),
        mergeMap(() => this.actions$.pipe(
          ofActionSuccessful(CreatePurchaseAction),
          take(1)
        )),
        untilDestroyed(this),
      )
      .subscribe(() => {
        this.router.navigate(['/purchase-created'])
        this.toastr.success('Purchase created successfully', 'Payment success')
      })
    // stop loading steps
    this.actions$
      .pipe(ofActionCompleted(SetBillingAction, CreatePurchaseAction), untilDestroyed(this))
      .subscribe(res => this.store.dispatch(new StopLoadingStepAction()))
    // handle errors
    this.actions$
      .pipe(ofActionErrored(SetBillingAction), untilDestroyed(this))
      .subscribe(() => this.store.dispatch(new SetErrorStepAction('Error validando metodo de pago')))
    this.actions$
      .pipe(ofActionErrored(CreatePurchaseAction), untilDestroyed(this))
      .subscribe(() => this.store.dispatch(new SetErrorStepAction('Error creando pago')))

  }
}
