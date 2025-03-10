import { Injectable } from '@angular/core';
import { Action, Selector, State, StateContext } from '@ngxs/store';
import { UserModel } from '@core/models/user.model';
import { SetUserAction } from '@core/store/actions/user.actions';

@State<UserModel>({
    name: 'user',
    defaults: {
        id: -1,
        fullname: 'Guest',
        email: 'guest@guest.com',
        address: {
            street: '123 Main St',
            city: 'Springfield'
        }
    }
})
@Injectable({
    providedIn: 'root'
})
export class UserState {

    @Action(SetUserAction)
    setUser(ctx: StateContext<UserModel>, action: SetUserAction) {
        ctx.setState(action.user);
    }

    @Selector()
    static getState(state: UserModel) {
        return state;
    }

}