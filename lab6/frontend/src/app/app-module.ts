import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrandListComponent } from './component/brand-list/brand-list.component';
import { BrandFormComponent } from './component/brand-form/brand-form.component';
import { BrandDetailsComponent } from './component/brand-details/brand-details.component';
import { CarModelFormComponent } from './component/car-model-form/car-model-form.component';
import { CarModelDetailsComponent } from './component/car-model-details/car-model-details.component';

@NgModule({
  declarations: [
    AppComponent,
    BrandListComponent,
    BrandFormComponent,
    BrandDetailsComponent,
    CarModelFormComponent,
    CarModelDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
  ],
  providers: [
    provideHttpClient() 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }