import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appCheckoutButton]'
})
export class CheckoutButtonDirective {
  isDisabled = false;

  @Input()
  set disabled(value: boolean) {
    this.isDisabled = value;
    if (this.isDisabled) {
      this.el.nativeElement.classList.add('opacity-50', 'cursor-not-allowed');
      this.el.nativeElement.setAttribute('disabled', 'true');
    } else {
      this.el.nativeElement.classList.remove('opacity-50', 'cursor-not-allowed');
      this.el.nativeElement.removeAttribute('disabled');
    }
  }

  constructor(private el: ElementRef) { }

  ngOnInit() {
    this.el.nativeElement.classList.add(
      'inline-block',
      'rounded-full',
      'bg-blue-600',
      'px-8', 'py-3',
      'w-56',
      'text-sm',
      'font-medium',
      'text-white',
      'transition',
      'hover:scale-110',
      'hover:shadow-xl',
      'focus:outline-none',
      'focus:ring',
      'active:bg-blue-500'
    );
  }

}
