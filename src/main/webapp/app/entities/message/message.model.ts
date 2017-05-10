export class Message {
    constructor(
        public id?: number,
        public messageTime?: any,
        public messageText?: string,
        public freelancerId?: number,
        public clientId?: number,
        public proposalId?: number,
        public proposalStatusCatalogId?: number,
        public attachmentId?: number,
    ) {
    }
}
