export enum Delivery {
    FREE = 'FREE',
    EXPRESS = 'EXPRESS',
}

export interface ShippingModel {
    delivery: Delivery;
    fullname: string;
    address: string;
    city: string;
}