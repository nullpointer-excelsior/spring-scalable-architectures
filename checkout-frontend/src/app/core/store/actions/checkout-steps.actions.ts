export class SetCurrentStepAction {
    static readonly type = '[CheckoutSteps] Set Current Step';
    constructor(public step: number) {}
}

export class StartLoadingStepAction {
    static readonly type = '[CheckoutSteps] Start Loading Step';
    constructor(public message: string) {}
}

export class StopLoadingStepAction {
    static readonly type = '[CheckoutSteps] Stop Loading Step';
    constructor() {}
}

export class SetErrorStepAction {
    static readonly type = '[CheckoutSteps] Set Error Step';
    constructor(public message: string) {}
}

export class CleanErrorStepAction {
    static readonly type = '[CheckoutSteps] Clean Error Step';
    constructor() {}
}