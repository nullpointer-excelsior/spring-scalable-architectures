import { Component, inject } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Delivery } from '@core/models/shipping.model';
import { CreateRandomCheckoutAction, SetShippingAction } from '@core/store/actions/checkout.actions';
import { SetCurrentStep } from '@core/store/actions/ui.actions';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { InputTextComponent } from '@shared/components/input-text/input-text.component';
import { BorderIndicatorDirective } from '@shared/directives/border-indicator.directive';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
    BorderIndicatorDirective,
    RouterLink,
    ReactiveFormsModule,
    InputTextComponent,
  ],
  templateUrl: './shipping.component.html'
})
export class ShippingComponent {

  private store = inject(Store);
  private formFactory = inject(FormFactoryService)
  public form: FormGroup;
  public deliveryOptions = Delivery

  constructor() {
    this.store.dispatch([
      new CreateRandomCheckoutAction(),
      new SetCurrentStep(1)
    ]).subscribe(() => this.form = this.formFactory.createShippingForm())
  }

  onContinue() {
    this.store.dispatch(new SetShippingAction({
      delivery: this.form.value.delivery
    }))
  }

}
