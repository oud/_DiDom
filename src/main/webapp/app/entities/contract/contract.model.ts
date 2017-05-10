export class Contract {
    constructor(
        public id?: number,
        public startTime?: any,
        public endTime?: any,
        public paymentAmount?: number,
        public clientId?: number,
        public freelancerId?: number,
        public proposalId?: number,
        public paymentTypeId?: number,
    ) {
    }
}
