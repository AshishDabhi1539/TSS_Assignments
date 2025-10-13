import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'meterToKm',
  standalone: true
})
export class MeterToKmPipe implements PipeTransform {
  transform(value: number, showUnit: boolean = true): string {
    if (!value && value !== 0) return '';
    
    const km = value / 1000;
    return showUnit ? `${km} km` : km.toString();
  }
}
