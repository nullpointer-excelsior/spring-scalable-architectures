import { Component, inject, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Delivery } from '@core/models/shipping.model';
import { SetShippingAction } from '@core/store/actions/checkout.actions';
import { FormFactoryService } from '@features/checkout/services/form-factory.service';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Store } from '@ngxs/store';
import { InputTextComponent } from '@shared/components/input-text/input-text.component';
import { CheckoutButtonDirective } from '@shared/directives/checkout-button.directive';

@UntilDestroy()
@Component({
  selector: 'app-shipping',
  imports: [
    CheckoutButtonDirective,
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
    this.formFactory.createShippingForm().pipe(
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
