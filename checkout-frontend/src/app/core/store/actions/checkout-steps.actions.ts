export class SetCurrentStep {
    static readonly type = '[UI] Set Current Step';
    constructor(public step: number) {}
}

export class StartLoadingStep {
    static readonly type = '[UI] Start Loading Step';
    constructor(public message: string) {}
}

export class StopLoadingStep {
    static readonly type = '[UI] Stop Loading Step';
    constructor() {}
}