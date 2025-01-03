import { Component, input } from '@angular/core';

@Component({
  selector: 'app-input-text',
  imports: [],
  templateUrl: './input-text.component.html',
  styles: ``
})
export class InputTextComponent {

  value = input('')
  title = input('')
  placeholder = input('')
  label = input('')
  id = input('')
}
