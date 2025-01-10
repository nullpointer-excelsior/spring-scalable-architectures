import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Delivery } from '@core/models/shipping.model';
import { CreateRandomCheckoutAction } from '@core/store/actions/create-random-checkout.action';
import { SetCurrentStep } from '@core/store/actions/set-current-step.action';
import { SetShippingAction } from '@core/store/actions/set-shipping.action';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
    BorderIndicatorDirective,
    RouterLink,
    ReactiveFormsModule,
  ],
  templateUrl: './shipping.component.html'
})
export class ShippingComponent {

  private store = inject(Store);
  private formFactory = inject(FormFactoryService)
  public form: FormGroup = this.formFactory.createShippingForm()
  public deliveryOptions = Delivery
  
  constructor() {
    this.store.dispatch([
      new CreateRandomCheckoutAction(),
      new SetCurrentStep(1)
    ])
  }

  onContinue() {
    this.store.dispatch(new SetShippingAction({
      delivery: this.form.value.delivery
    }))
  }

}
