
export interface ItemCartModel {
    sku: number;
    name: string;
    brand: string;
    price: number;
    quantity: number;
    image: string;
}

export interface CartModel {
    items: ItemCartModel[];
}