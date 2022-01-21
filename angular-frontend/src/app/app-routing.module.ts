import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChargingsessiondetailComponent } from './components/chargingsessions/chargingsessiondetail/chargingsessiondetail.component';
import { ChargingsessionsComponent } from './components/chargingsessions/chargingsessions.component';

const routes: Routes = [
  // { path: '', redirectTo: '/ladevorgaenge', pathMatch: 'full' },
  {
    path: 'ladevorgaenge',
    component: ChargingsessionsComponent,
  },
  {
    path: 'ladevorgaenge/:rfid',
    component: ChargingsessiondetailComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
