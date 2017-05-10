import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager  , DataUtils } from 'ng-jhipster';

import { Freelancer } from './freelancer.model';
import { FreelancerService } from './freelancer.service';

@Component({
    selector: 'jhi-freelancer-detail',
    templateUrl: './freelancer-detail.component.html'
})
export class FreelancerDetailComponent implements OnInit, OnDestroy {

    freelancer: Freelancer;
    private subscription: any;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: EventManager,
        private dataUtils: DataUtils,
        private freelancerService: FreelancerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFreelancers();
    }

    load(id) {
        this.freelancerService.find(id).subscribe((freelancer) => {
            this.freelancer = freelancer;
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

    registerChangeInFreelancers() {
        this.eventSubscriber = this.eventManager.subscribe('freelancerListModification', (response) => this.load(this.freelancer.id));
    }
}
