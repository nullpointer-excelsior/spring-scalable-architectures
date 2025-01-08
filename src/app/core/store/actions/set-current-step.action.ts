export class SetCurrentStep {
    static readonly type = '[UI] Set Current Step';
    constructor(public step: number) {}
}