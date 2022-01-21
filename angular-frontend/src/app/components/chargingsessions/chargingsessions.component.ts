import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { ChargingSession } from 'src/app/models/chargingsession';
import { ChargingsessionService } from 'src/app/services/chargingsession.service';
import { ContextService } from 'src/app/services/context.service';

@Component({
  selector: 'app-chargingsessions',
  templateUrl: './chargingsessions.component.html',
  styleUrls: ['./chargingsessions.component.scss'],
})
export class ChargingsessionsComponent implements OnInit {
  chargingSessions: ChargingSession[] = [];

  constructor(
    private chargingSessionService: ChargingsessionService,
    private contextService: ContextService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.contextService.pageTitle$.next('Rfid-Tags');
    this.loadChargingSession();
  }

  loadChargingSession(): void {
    this.chargingSessionService
      .getLatestSessionsByTagId()
      .pipe(
        tap((data) => {
          this.chargingSessions = data;
        })
      )
      .subscribe();
  }

  openRfidTag(session: ChargingSession): void {
    this.router.navigate(['ladevorgaenge/' + session.idTag]);
  }
}
