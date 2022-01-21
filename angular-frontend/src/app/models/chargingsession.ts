export interface ChargingSession {
  id: number;
  idTag: string;
  transactionId: number;
  startMeter: number;
  stopMeter: number;
  consumption: number;
  chargingStart: Date;
  chargingEnd: Date;
}
