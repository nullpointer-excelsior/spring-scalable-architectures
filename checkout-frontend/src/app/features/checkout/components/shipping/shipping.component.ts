import { Component, inject, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { Delivery } from '@core/models/shipping.model';
import { CreateRandomCheckoutAction, SetShippingAction } from '@core/store/actions/checkout.actions';
import { SetCurrentStep } from '@core/store/actions/ui.actions';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { Store } from '@ngxs/store';
import { InputTextComponent } from '@shared/components/input-text/input-text.component';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';
import { switchMap } from 'rxjs';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';

@UntilDestroy()
@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
    RouterLink,
    ReactiveFormsModule,
    InputTextComponent,
  ],
  templateUrl: './shipping.component.html'
})
export class ShippingComponent implements OnInit {

  private store = inject(Store);
  private formFactory = inject(FormFactoryService);
  public form: FormGroup;
  public deliveryOptions = Delivery;

  ngOnInit(): void {
    this.store.dispatch([
      new CreateRandomCheckoutAction(),
      new SetCurrentStep(1)
    ]).pipe(
      switchMap(() => this.formFactory.createShippingForm()),
      untilDestroyed(this)
    ).subscribe(form => this.form = form)
  }

  onContinue() {
    const value = this.form.value
    this.store.dispatch(new SetShippingAction({
      delivery: value.delivery,
      fullname: value.fullname,
      address: value.address,
      city: value.city
    }))
  }

}
