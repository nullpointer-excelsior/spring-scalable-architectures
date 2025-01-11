import { CommonModule } from '@angular/common';
import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-step-indicator',
  imports: [
    CommonModule,
    RouterLink,
  ],
  templateUrl: './step-indicator.component.html',
  styles: ``
})
export class StepIndicatorComponent {
  currentStep = input(0)
  step = input(0)
  name = input('')
  link = input('')
}
