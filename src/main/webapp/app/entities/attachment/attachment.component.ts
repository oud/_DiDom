import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, AlertService } from 'ng-jhipster';

import { Attachment } from './attachment.model';
import { AttachmentService } from './attachment.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-attachment',
    templateUrl: './attachment.component.html'
})
export class AttachmentComponent implements OnInit, OnDestroy {
attachments: Attachment[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private attachmentService: AttachmentService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.attachmentService.query().subscribe(
            (res: Response) => {
                this.attachments = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAttachments();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Attachment) {
        return item.id;
    }
    registerChangeInAttachments() {
        this.eventSubscriber = this.eventManager.subscribe('attachmentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
