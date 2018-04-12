import { AppMaterialModule } from './app-material.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { RequestsComponent } from './monitoring/requests/requests.component';
import { MonitoringService } from '@app/monitoring/monitoring.service';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
	declarations: [
		AppComponent,
		RequestsComponent
	],
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		HttpClientModule,
		AppMaterialModule
	],
	providers: [
		MonitoringService
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
