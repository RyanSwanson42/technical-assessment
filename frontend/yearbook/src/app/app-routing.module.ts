import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import { ErrorComponent } from './error/error.component';
import { FormComponent } from './form/form.component';
import { LookupComponent } from './lookup/lookup.component';

const routes : Routes = [
  { path:'', component: FormComponent},
  { path:'form', component: FormComponent},
  //{ path:'confirmation/:orderId/:firstName/:lastName/:address/:grade/:state/:city/:address/:zip', component: ConfirmationComponent},
  //{ path:'confirmation/:orderId/:firstName/:lastName', component: ConfirmationComponent},
  { path:'confirmation/:orderId/:firstName/:lastName/:grade/:state/:address/:zip', component: ConfirmationComponent},
  { path:'lookup', component: LookupComponent}
  //{ path:'**', component: ErrorComponent}
];

@NgModule({
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
