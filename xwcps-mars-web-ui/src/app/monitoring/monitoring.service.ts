import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/Observable";
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';
import { environment } from '@env/environment';

@Injectable()
export class MonitoringService {
	private endpoint: string = environment.monitoringServiceEndpoint;

    constructor(private http: HttpClient) {}

    getAllRequests(): Observable<Request[]> {
        return this.http.get<any>(`${this.endpoint}/requests`).pipe(
			map(request => request.entity.body)
		);
    }

    deleteRequest(id: string) {
        return this.http.delete(`${this.endpoint}/requests/${id}`)
            .pipe(
                catchError(() => of([]))
            ).subscribe();
    }
}