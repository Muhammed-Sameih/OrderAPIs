package com.example.orderapis.util;

import com.example.orderapis.exception.RecordNotFoundException;
import com.example.orderapis.model.ResponseModel;
import com.example.orderapis.model.bank.DepositRequest;
import com.example.orderapis.model.bank.WithdrawRequest;
import com.example.orderapis.model.coupon.CouponResponseDTO;
import com.example.orderapis.model.coupon.CouponServiceRequest;
import com.example.orderapis.model.orderItem.ProductServiceResponse;
import com.example.orderapis.model.store.StoreServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@Component
public class RestApiClient {


   private final RestTemplate restTemplate;

    public RestApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CouponResponseDTO getCouponByCode(String couponCode) {
        String couponUrl = "URL_TO_COUPON_SERVICE"; // Replace with the actual URL
        ResponseEntity<CouponResponseDTO> response = restTemplate.getForEntity(couponUrl, CouponResponseDTO.class, couponCode);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        else
            throw new RecordNotFoundException("Coupon application failed");
    }

    public ProductServiceResponse getProductPriceByCode(String productCode) {
        String productUrl = "URL_TO_PRODUCT_SERVICE"; // Replace with the actual URL
        ResponseEntity<ProductServiceResponse> response = restTemplate.getForEntity(productUrl, ProductServiceResponse.class, productCode);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        else
            throw new RecordNotFoundException("Product application failed");
    }

    public List<StoreServiceResponse> getStoresByProductAndQuantity(String productCode, Long quantity) {
        String storeUrl = "URL_TO_STORE_SERVICE"; // Replace with the actual URL
        ResponseEntity<StoreServiceResponse[]> response = restTemplate.getForEntity(storeUrl, StoreServiceResponse[].class, productCode, quantity);
        if(response.getStatusCode() == HttpStatus.OK)
            return Arrays.asList(response.getBody());
        else
            throw new RecordNotFoundException("Withdraw application failed");
    }

    public ResponseModel postDeposit(DepositRequest depositRequest) {
        String depositUrl = "URL_TO_DEPOSIT_SERVICE"; // Replace with the actual URL
        ResponseEntity<ResponseModel> response = restTemplate.postForEntity(depositUrl, depositRequest, ResponseModel.class);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        else
            throw new RecordNotFoundException("Withdraw application failed");
    }

    public ResponseModel postWithdraw(WithdrawRequest withdrawRequest) {
        String withdrawUrl = "URL_TO_WITHDRAW_SERVICE"; // Replace with the actual URL
        ResponseEntity<ResponseModel> response = restTemplate.postForEntity(withdrawUrl, withdrawRequest, ResponseModel.class);
        if(response.getStatusCode() == HttpStatus.OK)
            return response.getBody();
        else
            throw new RecordNotFoundException("Withdraw application failed");
    }

    public ResponseModel postApplyCoupon(CouponServiceRequest couponServiceRequest) {
        String couponApplyUrl = "URL_TO_COUPON_APPLY_SERVICE"; // Replace with the actual URL
        ResponseEntity<ResponseModel> response = restTemplate.postForEntity(couponApplyUrl, couponServiceRequest, ResponseModel.class);
        return response.getBody();
    }
}


