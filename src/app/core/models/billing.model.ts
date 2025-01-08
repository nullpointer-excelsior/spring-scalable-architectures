
export interface ContactInfoModel {
    fullname: string;
    address: string;
    city: string;
}

export interface CreditCardModel {
    cardName: string;
    cardNumber: string;
    expiration: string;
    cvv: number;
}

export enum PaymentMethod {
    CreditCard = 'CreditCard',
    GiftCard = 'GiftCard'
}

export interface PaymentModel {
    method: PaymentMethod;
    details: CreditCardModel;
}

export interface BillingModel {
    contactInfo: ContactInfoModel;
    payment: PaymentModel;
}