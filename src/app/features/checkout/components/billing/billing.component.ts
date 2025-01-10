import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PaymentMethod } from '@core/models/billing.model';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { InputTextComponent } from "@shared/components/input-text/input-text.component";
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { SetCurrentStep } from '@core/store/actions/ui.actions';
import { SetBillingAction } from '@core/store/actions/checkout.actions';

@Component({
  selector: 'app-billing',
  imports: [
    BorderIndicatorDirective,
    InputTextComponent,
    CheckoutButtonDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './billing.component.html'
})
export class BillingComponent {
  
  private store = inject(Store)
  private factory = inject(FormFactoryService)
  public form: FormGroup = this.factory.createBillingForm();

  constructor() {
    this.store.dispatch(new SetCurrentStep(2))
  }

  onContinue() {
    const value = this.form.value
    this.store.dispatch(new SetBillingAction({
      contactInfo: {
        fullname: value.fullname,
        address: value.address,
        city: value.city,
      },
      payment: {
        method: PaymentMethod.CreditCard,
        details: {
          cardName: value.cardName,
          cardNumber: value.cardNumber,
          expiration: value.expiration,
          cvv: value.cvv
        }
      }
    }))
  }

}
