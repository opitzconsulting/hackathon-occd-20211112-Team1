import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChargingSession } from '../models/chargingsession';

@Injectable({
  providedIn: 'root',
})
export class ChargingsessionService {
  rootUrl =
    'https://ocpp16-alb-17yv3g1k73aqm-30456640.eu-central-1.elb.amazonaws.com/gui/api/v1/';

  constructor(private httpClient: HttpClient) {}

  public getChargingSessionsByRfidtag(
    rfidTag: string
  ): Observable<ChargingSession[]> {
    return this.httpClient.get<ChargingSession[]>(
      this.rootUrl + 'consumption/chargingsessions/tag/' + rfidTag
    );
  }

  public getLatestSessionsByTagId(): Observable<ChargingSession[]> {
    return this.httpClient.get<ChargingSession[]>(
      this.rootUrl + 'consumption/chargingsessions/latestSessionByTagId'
    );
  }

  public getChargingSessions(): Observable<ChargingSession[]> {
    return this.httpClient.get<ChargingSession[]>(
      this.rootUrl + 'consumption/chargingsessions'
    );
  }
}
