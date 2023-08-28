package com.example.orderapis.service.impl;

import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.orderapis.entity.Coupon;
import com.example.orderapis.mapper.CouponMapper;
import com.example.orderapis.model.coupon.CouponModelForRequest;
import com.example.orderapis.model.coupon.CouponModelForUseCoupon;
import com.example.orderapis.model.coupon.CouponModelForResponse;
import com.example.orderapis.repo.CouponRepo;
import com.example.orderapis.util.RestApiClient;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @Mock
    private CouponRepo couponRepo;
    @Mock
    private RestApiClient restApiClient;
    @Mock
    private CouponMapper couponMapper;
    @InjectMocks
    private CouponServiceImpl couponService;
    private Coupon valueCoupon;
    private Coupon percentageCoupon;
    private CouponModelForRequest couponModelForRequest;
    private CouponModelForResponse couponModelForResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        percentageCoupon = new Coupon(1L,
                "CODE123",
                "PERCENTAGE",
                BigDecimal.valueOf(20));
        valueCoupon = new Coupon(1L,
                "CODE456",
                "VALUE",
                BigDecimal.valueOf(50));
        couponModelForRequest = new CouponModelForRequest("CODE123");
        couponModelForResponse = new CouponModelForResponse("CODE123",
                "PERCENTAGE",
                BigDecimal.valueOf(20));
    }
    @Test
    public void createOrFind_shouldFindExistingCoupon() {

        when(couponMapper.toEntity(couponModelForRequest)).thenReturn(percentageCoupon);
        when(couponMapper.toEntity(couponModelForResponse)).thenReturn(percentageCoupon);
        when(couponRepo.findByCode("CODE123")).thenReturn(Optional.of(percentageCoupon));
        when(restApiClient.getCouponByCode("CODE123")).thenReturn(couponModelForResponse);

        Coupon result = couponService.createOrFindCoupon(this.couponModelForRequest);

        assertEquals(percentageCoupon, result);

        verify(couponRepo).findByCode("CODE123");
        verifyNoMoreInteractions(couponRepo, restApiClient);
    }
    @Test
    public void createOrFind_ShouldCreateNewCoupon() {
        when(couponMapper.toEntity(couponModelForRequest)).thenReturn(percentageCoupon);
        when(couponMapper.toEntity(couponModelForResponse)).thenReturn(percentageCoupon);
        when(couponRepo.findByCode("CODE123")).thenReturn(Optional.empty());
        when(couponRepo.save(any(Coupon.class))).thenReturn(percentageCoupon);
        when(restApiClient.getCouponByCode("CODE123")).thenReturn(couponModelForResponse);

        Coupon result = couponService.createOrFindCoupon(couponModelForRequest);

        assertEquals(percentageCoupon, result);

        verify(couponRepo).findByCode("CODE123");
        verify(couponRepo).save(percentageCoupon);
        verifyNoMoreInteractions(couponRepo, restApiClient);
    }
    @Test
    public void shouldApplyCouponDiscountForValueCoupon() {
        BigDecimal totalBeforeDiscount = BigDecimal.valueOf(200);
        CouponModelForUseCoupon couponModelForUseCouponForValue = new CouponModelForUseCoupon("ORDER123",BigDecimal.valueOf(200),BigDecimal.valueOf(150));

        BigDecimal result = couponService.applyCouponDiscount(valueCoupon, totalBeforeDiscount, "ORDER123");

        assertEquals(BigDecimal.valueOf(150), result);

        verify(restApiClient).postApplyCoupon(couponModelForUseCouponForValue, "CODE456");
        verifyNoMoreInteractions(couponRepo, restApiClient);
    }
    @Test
    public void shouldApplyCouponDiscountForPercentageCoupon() {
        BigDecimal totalBeforeDiscount = BigDecimal.valueOf(200);
        CouponModelForUseCoupon couponModelForUseCouponForPercentage = new CouponModelForUseCoupon("ORDER123",BigDecimal.valueOf(200),BigDecimal.valueOf(160));

        BigDecimal result = couponService.applyCouponDiscount(percentageCoupon, totalBeforeDiscount, "ORDER123");

        assertEquals(BigDecimal.valueOf(160), result);

        verify(restApiClient).postApplyCoupon(couponModelForUseCouponForPercentage, "CODE123");
        verifyNoMoreInteractions(couponRepo, restApiClient);
    }
    @Test
    public void shouldCalculateTotalPriceAfterValueCouponDiscount() {
        BigDecimal totalPriceBeforeDiscount = BigDecimal.valueOf(200);

        BigDecimal result = couponService.calculateTotalPriceAfterDiscount(valueCoupon, totalPriceBeforeDiscount);

        assertEquals(BigDecimal.valueOf(150), result);
    }
    @Test
    public void shouldCalculateTotalPriceAfterPercentageCouponDiscount() {
        BigDecimal totalPriceBeforeDiscount = BigDecimal.valueOf(200);

        BigDecimal result = couponService.calculateTotalPriceAfterDiscount(percentageCoupon, totalPriceBeforeDiscount);

        assertEquals(BigDecimal.valueOf(160), result);
    }

    @Test
    public void shouldCalculateTotalPriceAfterValueCouponDiscountAndAvoidNegativeValue() {
        BigDecimal totalPriceBeforeDiscount = BigDecimal.valueOf(100);

        // Create a VALUE coupon with a discount larger than the total price
        Coupon valueCoupon = new Coupon(1L, "CODE123", "VALUE", BigDecimal.valueOf(120));

        BigDecimal result = couponService.calculateTotalPriceAfterDiscount(valueCoupon, totalPriceBeforeDiscount);

        assertEquals(BigDecimal.ZERO, result);
    }

}
