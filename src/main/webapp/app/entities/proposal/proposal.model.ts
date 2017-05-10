export class Proposal {
    constructor(
        public id?: number,
        public proposalTime?: any,
        public paymentAmount?: number,
        public clientGrade?: number,
        public clientComment?: string,
        public freelancerGrade?: number,
        public freelancerComment?: string,
        public jobId?: number,
        public freelancerId?: number,
        public paymentTypeId?: number,
        public currentProposalStatusId?: number,
        public contractId?: number,
        public messageId?: number,
    ) {
    }
}
