import { UserModel } from "@core/models/user.model";

export class SetUserAction {
    static readonly type = '[User] SetUser';
    constructor(public user: UserModel) {}
}