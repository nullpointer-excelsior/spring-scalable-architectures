export type ValidatePaymentMethodRequest = {
  method: string;
  details: Record<string, number | string>;
}

export type ValidatePaymentMethodResponse = {
  method: string;
  valid: boolean;
}

export type CreateCartRequest = {
  user: {
    id: number;
    email: string;
  },
  products: {
    sku: number;
    name: string;
    brand: string;
    image: string;
    price: number;
    quantity: number;
  }[]
}

export type UpdateProductsRequest = {
  products: {
    sku: number;
    name: string;
    brand: string;
    image: string;
    price: number;
    quantity: number;
  }[]
}

export type CartResponse = {
  id: number
} & CreateCartRequest


export type CreatePurchaseRequest = {
  order: {
    products: {
      sku: number;
      name: string;
      price: number;
      quantity: number;
    }[],
    amount: number;
  },
  payment: {
    method: string;
    amount: number;
    details: Record<string, number | string>
  },
  shipping: {
    fullname: string;
    address: string;
    city: string;
    option: string;
  }
}

export type PurchaseCretaedResponse = {
  purchaseId: number;
  purchaseRequestId: number;
}