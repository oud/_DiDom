import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Freelancer } from './freelancer.model';
import { FreelancerService } from './freelancer.service';
@Injectable()
export class FreelancerPopupService {
    private isOpen = false;
    constructor(
        private modalService: NgbModal,
        private router: Router,
        private freelancerService: FreelancerService

    ) {}

    open(component: Component, id?: number | any): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;

        if (id) {
            this.freelancerService.find(id).subscribe((freelancer) => {
                this.freelancerModalRef(component, freelancer);
            });
        } else {
            return this.freelancerModalRef(component, new Freelancer());
        }
    }

    freelancerModalRef(component: Component, freelancer: Freelancer): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.freelancer = freelancer;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.isOpen = false;
        });
        return modalRef;
    }
}
