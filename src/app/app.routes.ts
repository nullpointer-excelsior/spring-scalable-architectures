import { Routes } from '@angular/router';
import { ShippingComponent } from './components/shipping/shipping.component';
import { BillingComponent } from './components/billing/billing.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';

export const routes: Routes = [
    { path: '', redirectTo: 'shipping', pathMatch: 'full' },
    { path: 'shipping', component: ShippingComponent },
    { path: 'billing', component: BillingComponent },
    { path: 'confirmation', component: ConfirmationComponent },
];
