import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-confirmation-item-summary',
  imports: [],
  templateUrl: './confirmation-item-summary.component.html',
  styles: ``
})
export class ConfirmationItemSummaryComponent {
  @Input()
  name: string = '';
  @Input()
  value: string = '';
}
