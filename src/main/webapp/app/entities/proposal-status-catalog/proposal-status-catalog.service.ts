import { Injectable } from '@angular/core';
import { Http, Response, URLSearchParams, BaseRequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { ProposalStatusCatalog } from './proposal-status-catalog.model';
@Injectable()
export class ProposalStatusCatalogService {

    private resourceUrl = 'api/proposal-status-catalogs';

    constructor(private http: Http) { }

    create(proposalStatusCatalog: ProposalStatusCatalog): Observable<ProposalStatusCatalog> {
        const copy: ProposalStatusCatalog = Object.assign({}, proposalStatusCatalog);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(proposalStatusCatalog: ProposalStatusCatalog): Observable<ProposalStatusCatalog> {
        const copy: ProposalStatusCatalog = Object.assign({}, proposalStatusCatalog);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<ProposalStatusCatalog> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<Response> {
        const options = this.createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
        ;
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }
    private createRequestOption(req?: any): BaseRequestOptions {
        const options: BaseRequestOptions = new BaseRequestOptions();
        if (req) {
            const params: URLSearchParams = new URLSearchParams();
            params.set('page', req.page);
            params.set('size', req.size);
            if (req.sort) {
                params.paramsMap.set('sort', req.sort);
            }
            params.set('query', req.query);

            options.search = params;
        }
        return options;
    }
}
