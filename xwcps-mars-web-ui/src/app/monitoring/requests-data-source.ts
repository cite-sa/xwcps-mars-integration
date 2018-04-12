import { DataSource } from "@angular/cdk/table";
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Observable } from "rxjs/Observable";
import { of } from 'rxjs/observable/of';
import { map, catchError, finalize } from 'rxjs/operators';


import { MonitoringService } from "@app/monitoring/monitoring.service";
import { DetailedRequest } from "@app/monitoring/requests/requests.component";

export class RequestsDataSource implements DataSource<Request> {

    private requestsSubject = new BehaviorSubject<Request[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);

    public loading$ = this.loadingSubject.asObservable();

    constructor(private monitoringService: MonitoringService) {}

    connect(): Observable<Request[]> {
		// const rows = [];
		// this.monitoringService.getAllRequests().subscribe(requests => {
		// 	requests.forEach(element => rows.push(element, { detailRow: true, element }));
		// });

		// console.log("ROWS");
		// console.log(rows);
		// return of(rows);
		return this.requestsSubject.asObservable();
		// return this.monitoringService.getAllRequests();
    }

    disconnect(): void {
        this.requestsSubject.complete();
        this.loadingSubject.complete();
    }

    loadRequests() {
        this.loadingSubject.next(true);

        this.monitoringService.getAllRequests()
        .pipe(
			catchError(() => of([])),
        	finalize(() => this.loadingSubject.next(false))
		).subscribe((requests: Request[]) => {
			const rows: any = [];
			// console.log("REQUESTS");
			console.log(requests);
			requests.forEach((request: Request) => rows.push(request, { detailRow: true, request }));
			console.log(rows);
			// requests.forEach(element => rows.push(element, { detailRow: true, element }));
			return this.requestsSubject.next(rows);
		});
    }
}