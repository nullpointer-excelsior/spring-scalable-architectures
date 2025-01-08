export enum Delivery {
    Standard = 'Standard',
    Express = 'Express',
}

export interface ShippingModel {
    delivery: Delivery;
}