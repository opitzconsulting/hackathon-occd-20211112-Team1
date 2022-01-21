import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ChargingSession } from 'src/app/models/chargingsession';

@Component({
  selector: 'app-chargingsessioncard',
  templateUrl: './chargingsessioncard.component.html',
  styleUrls: ['./chargingsessioncard.component.scss'],
})
export class ChargingsessioncardComponent implements OnInit {
  @Input() chargingSession: ChargingSession | undefined;
  @Output() open = new EventEmitter<ChargingSession>();

  constructor() {}

  ngOnInit(): void {}

  openDetails(): void {
    this.open.emit(this.chargingSession);
  }
}
