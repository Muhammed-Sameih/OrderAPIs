package com.example.orderapis.service.impl;

import com.example.orderapis.entity.OrderItem;
import com.example.orderapis.mapper.OrderItemMapper;
import com.example.orderapis.model.orderItem.OrderItemModelForRequest;
import com.example.orderapis.model.product.ProductModel;
import com.example.orderapis.repo.OrderItemRepo;
import com.example.orderapis.util.RestApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceImplTest {
    @Mock
    private OrderItemRepo orderItemRepo;
    @Mock
    private OrderItemMapper orderItemMapper;
    @Mock
    private RestApiClient restApiClient;
    @InjectMocks
    private OrderItemServiceImpl orderItemService;
    private OrderItemModelForRequest itemModel;
    private ProductModel productModel;
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        itemModel = new OrderItemModelForRequest("P-NK-1",
                2L);
        productModel = new ProductModel(1L,
                "Nike Air Max 90",
                "P-NK-1",
                "The Nike Air Max 90 is a retro running shoe with Max Air® in the heel for comfort and cushioning. ",
                BigDecimal.valueOf(119.99),
                "Shoes",
                "Nike",
                "https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/f9e940d3-2192-434e-a017-072303ce2f14/air-max-90-mens-shoes-6mml4F.png",
                true,
                5);
        orderItem = new OrderItem(1L,
                "ORDER_ITEM_CODE",
                "P-NK-1",
                2L,
                BigDecimal.valueOf(119.99),
                null);
    }

    @Test
    void createList_shouldCreateOrderItems() {
        // Arrange
        List<OrderItemModelForRequest> items = new ArrayList<>();
        items.add(itemModel);

        Mockito.when(orderItemMapper.toEntity(itemModel)).thenReturn(orderItem);
        Mockito.when(restApiClient.getProductPriceByCode("P-NK-1")).thenReturn(productModel);
        Mockito.when(orderItemRepo.saveAll(Mockito.any())).thenReturn(Collections.singletonList(orderItem));
        // Act
        List<OrderItem> actualItems = orderItemService.createList(items);

        // Assert
        assertThat(actualItems).hasSize(1);
        assertThat(actualItems.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(119.99));
        Mockito.verify(orderItemRepo).saveAll(Mockito.anyList());
    }
}
