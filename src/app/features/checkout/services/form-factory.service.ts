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
            fullname: [billing.fullname, Validators.required],
            address: [billing.address, Validators.required],
            city: [billing.city, Validators.required],
            cardName: [billing.cardName, Validators.required],
            cardNumber: [billing.cardNumber, Validators.required],
            expiration: [billing.expiration, Validators.required],
            cvv: [billing.cvv, Validators.required]
        });

    }

    createShippingForm() {
        const delivery = this.store.selectSnapshot(CheckoutState.getShippingDelivery)
        const billing = this.store.selectSnapshot(CheckoutState.getBillingForm)
        return this.formBuilder.group({
            delivery: [delivery, Validators.required],
            fullname: [billing.fullname, Validators.required],
            address: [billing.address, Validators.required],
            city: [billing.city, Validators.required],
        })
    }
}