import { UserModel } from "@core/models/user.model";

export class SetUserAction {
    static readonly type = '[User] Set User';
    constructor(public user: UserModel) {}
}