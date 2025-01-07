
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

export interface PaymentMethodModel {
    type: string;
    payment: CreditCardModel;
}

export interface BillingModel {
    contactInfo: ContactInfoModel;
    paymentMethod: PaymentMethodModel;
}