export interface AddressModel {
    street: string;
    city: string;
}

export interface UserModel { 
    id: number;
    fullname: string;
    email: string;
    address: AddressModel;
}
