import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, AlertService } from 'ng-jhipster';

import { PaymentType } from './payment-type.model';
import { PaymentTypeService } from './payment-type.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-payment-type',
    templateUrl: './payment-type.component.html'
})
export class PaymentTypeComponent implements OnInit, OnDestroy {
paymentTypes: PaymentType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private paymentTypeService: PaymentTypeService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.paymentTypeService.query().subscribe(
            (res: Response) => {
                this.paymentTypes = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInPaymentTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: PaymentType) {
        return item.id;
    }
    registerChangeInPaymentTypes() {
        this.eventSubscriber = this.eventManager.subscribe('paymentTypeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
