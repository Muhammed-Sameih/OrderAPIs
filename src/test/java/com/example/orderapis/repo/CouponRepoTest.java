package com.example.orderapis.repo;

import com.example.orderapis.entity.Coupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CouponRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CouponRepo couponRepo;

    private Coupon expectedCoupon;

    @BeforeEach
    void setUp() {
        expectedCoupon = entityManager.merge(new Coupon(
                1L,
                "C-123",
                "VALUE",
                BigDecimal.valueOf(10)));
    }
    @Test
    void findByCode_shouldFindCouponIfCodeExists() {
        // Act
        Optional<Coupon> actualCoupon = couponRepo.findByCode(expectedCoupon.getCode());

        // Assert
        assertThat(actualCoupon).isPresent().get().isEqualTo(expectedCoupon);
    }

    @Test
    void findByCode_shouldNotFindCouponWithInvalidCode() {
        // Act
        Optional<Coupon> actualCoupon = couponRepo.findByCode("INVALID");

        // Assert
        assertThat(actualCoupon).isEmpty();
    }

    @Test
    void findByCode_shouldReturnEmptyOptionalIfCodeIsNull() {
        // Act
        Optional<Coupon> actualCoupon = couponRepo.findByCode(null);

        // Assert
        assertThat(actualCoupon).isEmpty();
    }

}
