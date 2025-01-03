import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appBorderIndicator]'
})
export class BorderIndicatorDirective {

  constructor(private el: ElementRef) {}

  ngOnInit() {
    this.el.nativeElement.classList.add('border', 'border-blue-500', 'rounded-md', 'p-2.5', 'm-2.5');
  }
}
