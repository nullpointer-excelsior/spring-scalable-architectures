import { Injectable } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { CheckoutState } from "@core/store/state/checkout.state";
import { Store } from "@ngxs/store";

@Injectable({
    providedIn: 'root'
})
export class FormFactoryService {

    constructor(private store: Store, private formBuilder: FormBuilder) { }

    createBillingForm() {
        const billing = this.store.selectSnapshot(CheckoutState.getBillingForm)
        return this.formBuilder.group({
            dni: [billing.dni, Validators.required],
            fullname: [billing.fullname, Validators.required],
            cardName: [billing.cardName, Validators.required],
            cardNumber: [billing.cardNumber, Validators.required],
            expiration: [billing.expiration, Validators.required],
            cvv: [billing.cvv, Validators.required]
        });

    }

    createShippingForm() {
        const shipping = this.store.selectSnapshot(CheckoutState.getShipping)
        return this.formBuilder.group({
            delivery: [shipping.delivery, Validators.required],
            fullname: [shipping.fullname, Validators.required],
            address: [shipping.address, Validators.required],
            city: [shipping.city, Validators.required],
        })
    }
}