
const enum Duration {
    'HOUR',
    'DAY',
    'MONTH'

};

const enum Complexity {
    'EASY',
    'INTERMEDIATE',
    'HARD'

};
export class Job {
    constructor(
        public id?: number,
        public title?: string,
        public description?: string,
        public paymentAmont?: number,
        public expectedDuration?: Duration,
        public complexity?: Complexity,
        public paymentTypeId?: number,
        public clientId?: number,
        public proposalId?: number,
        public mainSkillId?: number,
    ) {
    }
}
