import { Directive, ElementRef, Renderer2, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appBgColor]',
  standalone: true
})
export class BgColorDirective {
  @Input('appBgColor') hoverColor: string = 'lightblue'; // On hover
  @Input() defaultColor: string = 'transparent'; // On leave
  @Input() clickColor: string = 'yellow'; // On click

  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter')
  onEnter() {
    this.changeColor(this.hoverColor);
  }

  @HostListener('mouseleave')
  onLeave() {
    this.changeColor(this.defaultColor);
  }

  @HostListener('click')
  onClick() {
    this.changeColor(this.clickColor);
  }

  private changeColor(color: string) {
    this.renderer.setStyle(this.el.nativeElement, 'backgroundColor', color);
    this.renderer.setStyle(this.el.nativeElement, 'transition', 'background-color 0.3s ease');
  }
}
