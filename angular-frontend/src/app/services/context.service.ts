import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContextService {
  public pageTitle$ = new BehaviorSubject<string>('Startseite');

  constructor() {}
}
