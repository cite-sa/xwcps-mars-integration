import { Component, OnInit } from '@angular/core';
import { MonitoringService } from '@app/monitoring/monitoring.service';
import { RequestsDataSource } from '@app/monitoring/requests-data-source';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
	selector: 'app-monitoring-requests',
	templateUrl: './requests.component.html',
	styleUrls: ['./requests.component.css'],
	animations: [
		trigger('detailExpand', [
			state('collapsed', style({height: '0px', minHeight: '0', visibility: 'hidden'})),
			state('expanded', style({height: '*', visibility: 'visible'})),
			transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
		])
	]
})
export class RequestsComponent implements OnInit {
	displayedColumns = ['id', 'status', 'startTime', 'endTime', 'deleteRequest'];
	requestsDataSource: RequestsDataSource;

	isExpansionDetailRow = (i: number, row: Object) => {
		console.log(row.hasOwnProperty('detailRow'));
		return row.hasOwnProperty('detailRow');
	}

	expandedElement: any;

	constructor(private monitoringService: MonitoringService) {}

	ngOnInit() {
		this.requestsDataSource = new RequestsDataSource(this.monitoringService);
		this.requestsDataSource.loadRequests();
	}

	hasStatusProcessing(request: Request): boolean {
		return request.status == RequestStatus.Processing;
	}

	deleteRequest(id: string, event) {
		event.stopPropagation();
		console.log("Delete: " + id);
		// this.monitoringService.deleteRequest(id);
	}
}

export interface Request {
	id: string;
	wcsRequest: string;
	marsRequest: object;
	status: RequestStatus;
	timings: RequestTimings
	polling: string;
	monitoring: string;
	startTime: Date;
	endTime: Date;
}

export interface DetailedRequest {
	detailRow: boolean;
	request: Request;
}

export enum RequestStatus {
	Completed = "COMPLETED",
    Processing = "PROCESSING",
    Error = "ERROR"
}

export interface RequestTimings {
	requestTranslation: number;
	marsRequest: number;
	rasdamanRegistration: number;
	wcsRequest: number;
	total: number;
}

const REQUEST_DATA: Request[] = [
	{
		id: "123",
		wcsRequest: "",
		marsRequest: { test: "test"},
		status: RequestStatus.Completed,
		timings: {
			requestTranslation: 10,
			marsRequest: 20,
			rasdamanRegistration: 30,
			wcsRequest: 40,
			total: 100
		},
		polling: "http://polling",
		monitoring: "http://monitoring",
		startTime: new Date(),
		endTime: new Date()}
];