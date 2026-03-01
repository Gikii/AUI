import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrandListComponent } from './component/brand-list/brand-list.component';
import { BrandFormComponent } from './component/brand-form/brand-form.component';
import { BrandDetailsComponent } from './component/brand-details/brand-details.component';
import { CarModelFormComponent } from './component/car-model-form/car-model-form.component';
import { CarModelDetailsComponent } from './component/car-model-details/car-model-details.component';

const routes: Routes = [
  { path: 'brands', component: BrandListComponent },
  { path: 'brands/add', component: BrandFormComponent },
  { path: 'brands/:id/edit', component: BrandFormComponent },
  { path: 'brands/:id', component: BrandDetailsComponent },
  { path: 'brands/:brandId/models/add', component: CarModelFormComponent },
  { path: 'brands/:brandId/models/:modelId/edit', component: CarModelFormComponent },
  { path: 'brands/:brandId/models/:modelId', component: CarModelDetailsComponent },
  { path: '', redirectTo: '/brands', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }