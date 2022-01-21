import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { tap } from 'rxjs/operators';
import { ChargingSession } from 'src/app/models/chargingsession';
import { ChargingsessionService } from 'src/app/services/chargingsession.service';
import { ContextService } from 'src/app/services/context.service';

@Component({
  selector: 'app-chargingsessiondetail',
  templateUrl: './chargingsessiondetail.component.html',
  styleUrls: ['./chargingsessiondetail.component.scss'],
})
export class ChargingsessiondetailComponent implements OnInit {
  displayedColumns: string[] = [
    'transactionId',
    'startMeter',
    'stopMeter',
    'consumption',
    'chargingStart',
    'chargingEnd',
  ];

  dataSource!: MatTableDataSource<ChargingSession>;
  chartDataSource: unknown;

  constructor(
    private chargingSessionsService: ChargingsessionService,
    private activatedRoute: ActivatedRoute,
    private contextService: ContextService
  ) {
    this.chartDataSource = {
      labels: [
        '21/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
        '22/01/2022',
      ],
      datasets: [
        {
          label: 'Verbrauch pro Tag',
          backgroundColor: 'blue',
          data: [
            5000, 1000, 3000, 4000, 4500, 7000, 1200, 5000, 4500, 1200, 1300,
            1700, 2300, 1200, 1100,
          ],
        },
      ],
    };
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((params) => {
      const rfidTag = params.get('rfid') ?? '';
      this.contextService.pageTitle$.next('Rfid-Tag ' + rfidTag);
      this.loadChargingSession(rfidTag);
    });
  }

  loadChargingSession(rfidTag: string): void {
    this.chargingSessionsService
      .getChargingSessionsByRfidtag(rfidTag)
      .pipe(
        tap((data) => {
          this.dataSource = new MatTableDataSource(data);
        })
      )
      .subscribe();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
