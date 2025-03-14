export interface PurchaseModel {
    id: number;
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