import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { PaymentMethod } from '@core/models/billing.model';
import { SetBillingAction } from '@core/store/actions/checkout.actions';
import { SetCurrentStep } from '@core/store/actions/ui.actions';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { InputTextComponent } from "@shared/components/input-text/input-text.component";
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-billing',
  imports: [
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
        dni: value.dni,
        fullname: value.fullname,
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
