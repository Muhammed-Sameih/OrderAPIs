package com.example.orderapis.util;

import com.example.orderapis.model.ResponseModel;
import com.example.orderapis.model.bank.DepositRequest;
import com.example.orderapis.model.bank.WithdrawRequest;
import com.example.orderapis.model.coupon.CouponModelForResponse;
import com.example.orderapis.model.coupon.CouponModelForUseCoupon;
import com.example.orderapis.model.product.ProductModelForConsume;
import com.example.orderapis.model.product.ProductModel;
import com.example.orderapis.model.shipping.ShippingModel;
import com.example.orderapis.model.store.StoreModelResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class RestApiClient {
    private final RestTemplate restTemplate;

    public RestApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // COUPON Service Requests
    public CouponModelForResponse getCouponByCode(String couponCode) {
        String couponUrl = "https://coupons-service-api.onrender.com/coupons/";
        try {
            ResponseEntity<CouponModelForResponse> response = restTemplate.getForEntity(couponUrl + couponCode, CouponModelForResponse.class, couponCode);
            return response.getBody();
        }catch (HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coupon not found");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Coupon service failed");
            }
        }
    }
    public ResponseModel postApplyCoupon(CouponModelForUseCoupon couponModel, String couponCode) {
        String couponApplyUrl = "https://coupons-service-api.onrender.com/coupons/" + couponCode + "/useCoupon";
        try {
            ResponseEntity<ResponseModel> response = restTemplate.postForEntity(couponApplyUrl, couponModel, ResponseModel.class);
            return response.getBody();
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coupon not found");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Coupon service failed h5a");
            }
        }

    }

    // PRODUCT Service Requests
    public ProductModel getProductPriceByCode(String productCode) {
        String productUrl = "https://product-catalog-api-service.onrender.com/products/code/" + productCode;
        try {
            ResponseEntity<ProductModel> response = restTemplate.getForEntity(productUrl, ProductModel.class);
            return response.getBody();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product service failed!");
            }
        }
    }

    // SHIPPING Service Requests
    public ResponseModel postShippingOrder(ShippingModel shippingModel){
        String depositUrl = "https://shipping-service-dr3e.onrender.com/shipments";
        try {
            ResponseEntity<ResponseModel> response = restTemplate.postForEntity(depositUrl, shippingModel, ResponseModel.class);
            return response.getBody();
        }catch(HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Shipping service failed!");
            }
        }
    }

    // STORE Service Requests
    public List<StoreModelResponse> consumeProductsFromStoresWithProductInfo(List<ProductModelForConsume> products) {
        String storeMicroserviceUrl = "https://store-service-api.onrender.com/stocks/make_order";
        try {
            ResponseEntity<StoreModelResponse[]> responseEntity = restTemplate.postForEntity(
                    storeMicroserviceUrl,
                    products,
                    StoreModelResponse[].class
            );
            return List.of(responseEntity.getBody());
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Store service failed!");
            }
        }
    }

    // BANK Service Requests
    public ResponseModel postDeposit(DepositRequest depositRequest) {
        String depositUrl = "https://bank-m-s.onrender.com/transaction/deposit";
        try {
            ResponseEntity<ResponseModel> response = restTemplate.postForEntity(depositUrl, depositRequest, ResponseModel.class);
            return response.getBody();
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Card number!");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Bank service failed!");
            }
        }
    }
    public ResponseModel postWithdraw(WithdrawRequest withdrawRequest) {
        String withdrawUrl = "https://bank-m-s.onrender.com/transaction/withdraw";
        try {
            ResponseEntity<ResponseModel> response = restTemplate.postForEntity(withdrawUrl, withdrawRequest, ResponseModel.class);
            return response.getBody();
        }catch(HttpClientErrorException ex){
            if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Card number or cvv!");
            } else if(ex.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Bank service failed!");
            }
        }
    }
}


