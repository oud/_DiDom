import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Job } from './job.model';
import { JobPopupService } from './job-popup.service';
import { JobService } from './job.service';
import { PaymentType, PaymentTypeService } from '../payment-type';
import { Client, ClientService } from '../client';
import { Skill, SkillService } from '../skill';

@Component({
    selector: 'jhi-job-dialog',
    templateUrl: './job-dialog.component.html'
})
export class JobDialogComponent implements OnInit {

    job: Job;
    authorities: any[];
    isSaving: boolean;

    paymenttypes: PaymentType[];

    clients: Client[];

    skills: Skill[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private jobService: JobService,
        private paymentTypeService: PaymentTypeService,
        private clientService: ClientService,
        private skillService: SkillService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.paymentTypeService.query().subscribe(
            (res: Response) => { this.paymenttypes = res.json(); }, (res: Response) => this.onError(res.json()));
        this.clientService.query().subscribe(
            (res: Response) => { this.clients = res.json(); }, (res: Response) => this.onError(res.json()));
        this.skillService.query().subscribe(
            (res: Response) => { this.skills = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.job.id !== undefined) {
            this.jobService.update(this.job)
                .subscribe((res: Job) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        } else {
            this.jobService.create(this.job)
                .subscribe((res: Job) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
        }
    }

    private onSaveSuccess(result: Job) {
        this.eventManager.broadcast({ name: 'jobListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    trackPaymentTypeById(index: number, item: PaymentType) {
        return item.id;
    }

    trackClientById(index: number, item: Client) {
        return item.id;
    }

    trackSkillById(index: number, item: Skill) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-job-popup',
    template: ''
})
export class JobPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private jobPopupService: JobPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.jobPopupService
                    .open(JobDialogComponent, params['id']);
            } else {
                this.modalRef = this.jobPopupService
                    .open(JobDialogComponent);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
