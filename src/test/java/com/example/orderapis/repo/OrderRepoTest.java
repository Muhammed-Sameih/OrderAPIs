package com.example.orderapis.repo;

import com.example.orderapis.entity.Coupon;
import com.example.orderapis.entity.Order;
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
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepoTest {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        Coupon coupon = new Coupon(null,"CODE123", "Percentage", BigDecimal.TEN);
        testEntityManager.persistAndFlush(coupon);

        Order order1 = new Order(null, coupon, LocalDateTime.now(), BigDecimal.valueOf(100),
                "test@example.com", "Pending", "ORDER123", null);
        Order order2 = new Order(null, coupon, LocalDateTime.now().minusDays(2), BigDecimal.valueOf(150),
                "other@example.com", "Shipped", "ORDER456", null);

        testEntityManager.persistAndFlush(order1);
        testEntityManager.persistAndFlush(order2);
    }

    @Test
    public void findOrdersByCustomerEmailAndOrderDateBetween_shouldReturnValidOrders() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        List<Order> foundOrders = orderRepo.findByCustomerEmailAndOrderDateBetween(
                "test@example.com", startDate, endDate);

        assertThat(foundOrders).hasSize(1);
        assertThat(foundOrders.get(0).getCustomerEmail()).isEqualTo("test@example.com");
    }

    @Test
    public void findOrdersByCustomerEmailAndOrderDateBetween_shouldReturnEmptyListWhenCustomerEmailIsNull() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(1);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1);

        List<Order> foundOrders = orderRepo.findByCustomerEmailAndOrderDateBetween(
                null, startDate, endDate);

        assertThat(foundOrders).isEmpty();
    }

    @Test
    public void findOrdersByCustomerEmailAndOrderDateBetween_shouldReturnEmptyListWhenDateRangeIsNull() {
        List<Order> foundOrders = orderRepo.findByCustomerEmailAndOrderDateBetween(
                "test@example.com", null, null);

        assertThat(foundOrders).isEmpty();
    }
}









