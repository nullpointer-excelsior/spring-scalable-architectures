import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { CartResponse, CreateCartRequest, CreatePurchaseRequest, PurchaseCretaedResponse, ValidatePaymentMethodRequest, ValidatePaymentMethodResponse } from "@app/core/dto/ecommerce.dto";
import { ProductModel } from "@core/models/product.model";
import { Observable } from "rxjs";
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class EcommerceApi {

    private http = inject(HttpClient)

    getProducts() {
        return this.http.get<ProductModel[]>(`${environment.checkoutUrl}/products`);
    }

    getCartById(cartId: number): Observable<CartResponse> {
        return this.http.get<CartResponse>(`${environment.checkoutUrl}/carts/${cartId}`);
    }

    createCart(request: CreateCartRequest): Observable<CartResponse> {
        return this.http.post<CartResponse>(`${environment.checkoutUrl}/carts`, request);
    }

    updateProductCart(cartId: number, product: ProductModel) {
        return this.http.patch<CartResponse>(`${environment.checkoutUrl}/carts/${cartId}/products/${product.sku}`, product)
    }

    deleteProductCart(cartId: number, product: ProductModel) {
        return this.http.delete(`${environment.checkoutUrl}/carts/${cartId}/products/${product.sku}`)
    }

    validatePaymentMethod(request: ValidatePaymentMethodRequest): Observable<ValidatePaymentMethodResponse> {
        return this.http.post<ValidatePaymentMethodResponse>(`${environment.checkoutUrl}/payment-methods/validate`, request);
    }

    processPurchase(request: CreatePurchaseRequest): Observable<PurchaseCretaedResponse>{
        return this.http.post<PurchaseCretaedResponse>(`${environment.checkoutUrl}/purchases/process`, request)
    } 

}

