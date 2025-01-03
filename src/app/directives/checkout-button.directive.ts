import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appCheckoutButton]'
})
export class CheckoutButtonDirective {

  constructor(private el: ElementRef) { }

  ngOnInit() {
    this.el.nativeElement.classList.add(
      'inline-block',
      'rounded',
      'bg-indigo-600',
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
      'active:bg-indigo-500'
    );
  }

}
