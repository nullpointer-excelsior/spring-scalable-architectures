import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { CartState } from '@core/store/state/cart.state';
import { CheckoutState } from '@core/store/state/checkout.state';
import { CheckoutStepsState } from '@core/store/state/checkout-steps.state';
import { UserState } from '@core/store/state/user.state';
import { withNgxsReduxDevtoolsPlugin } from '@ngxs/devtools-plugin';
import { provideStore } from '@ngxs/store';
import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideToastr } from 'ngx-toastr';
import { provideAnimations } from '@angular/platform-browser/animations';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(),
    provideToastr(),
    provideAnimations(),
    provideStore([
      CartState,
      UserState,
      CheckoutState,
      CheckoutStepsState
    ],
      withNgxsReduxDevtoolsPlugin()
    )
  ]
};
