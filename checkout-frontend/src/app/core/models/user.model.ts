export interface AddressModel {
    street: string;
    city: string;
}

export interface UserModel { 
    fullname: string;
    email: string;
    address: AddressModel;
}
