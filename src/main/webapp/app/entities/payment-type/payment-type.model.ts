export class PaymentType {
    constructor(
        public id?: number,
        public typeName?: string,
        public jobId?: number,
        public contractId?: number,
        public proposalId?: number,
    ) {
    }
}
