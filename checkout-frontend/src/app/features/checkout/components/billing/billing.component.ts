import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { PaymentMethod } from '@core/models/billing.model';
import { SetBillingAction } from '@core/store/actions/checkout.actions';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { InputTextComponent } from "@shared/components/input-text/input-text.component";
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-billing',
  imports: [
    InputTextComponent,
    CheckoutButtonDirective,
    ReactiveFormsModule,
  ],
  templateUrl: './billing.component.html'
})
export class BillingComponent {

  private store = inject(Store)
  private factory = inject(FormFactoryService)
  public form: FormGroup = this.factory.createBillingForm();

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
