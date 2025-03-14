import { Routes } from '@angular/router';
import { ShippingComponent } from './features/checkout/components/shipping/shipping.component';
import { BillingComponent } from './features/checkout/components/billing/billing.component';
import { ConfirmationComponent } from './features/checkout/components/confirmation/confirmation.component';
import { PurchaseCreatedComponent } from './features/checkout/components/purchase-created/purchase-created.component';

export const routes: Routes = [
    { path: '', redirectTo: 'shipping', pathMatch: 'full' },
    { path: 'shipping', component: ShippingComponent },
    { path: 'billing', component: BillingComponent },
    { path: 'confirmation', component: ConfirmationComponent },
    { path: 'purchase-created', component: PurchaseCreatedComponent },
];
