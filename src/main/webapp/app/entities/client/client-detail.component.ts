import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  , DataUtils } from 'ng-jhipster';

import { Client } from './client.model';
import { ClientService } from './client.service';

@Component({
    selector: 'jhi-client-detail',
    templateUrl: './client-detail.component.html'
})
export class ClientDetailComponent implements OnInit, OnDestroy {

    client: Client;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private dataUtils: DataUtils,
        private clientService: ClientService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInClients();
    }

    load(id) {
        this.clientService.find(id).subscribe((client) => {
            this.client = client;
        });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInClients() {
        this.eventSubscriber = this.eventManager.subscribe('clientListModification', (response) => this.load(this.client.id));
    }
}
