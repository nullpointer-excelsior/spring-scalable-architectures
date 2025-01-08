import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { withNgxsReduxDevtoolsPlugin } from '@ngxs/devtools-plugin';
import { provideStore } from '@ngxs/store';
import { CartState } from '@core/store/state/cart.state';
import { ShippingState } from '@core/store/state/shipping.state';
import { BillingState } from '@core/store/state/billing.state';
import { UserState } from '@core/store/state/user.state';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideStore([
      CartState,
      ShippingState,
      BillingState,
      UserState
    ],
      withNgxsReduxDevtoolsPlugin()
    )
  ]
};
