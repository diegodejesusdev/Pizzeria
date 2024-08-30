package com.dproject.pizzeria.persistence.repository;

import com.dproject.pizzeria.persistence.entity.OrderEntity;
import com.dproject.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findAllByDateOrderAfter(LocalDateTime date);
    List<OrderEntity> findAllByMethodOrderIn(List<String> methodOrder);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer_order = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrder(@Param("id") Integer idCustomer);

    @Query(value = """
            SELECT po.id_order AS idOrder, cu.name_customer AS nameCustomer, po.date_order AS dateOrder,
                   po.total_order AS totalOrder, GROUP_CONCAT(pi.name_pizza) AS namePizza
            FROM pizza_order po
                INNER JOIN customer cu ON po.id_customer_order = cu.id_customer
                INNER JOIN order_item oi ON po.id_order = oi.id_order
                INNER JOIN pizza pi ON oi.id_pizza = pi.id_pizza
                WHERE po.id_order = :idOrder
            GROUP BY po.id_order, cu.name_customer, po.date_order, po.total_order
            """, nativeQuery = true)
    OrderSummary findOrderSummaryById(@Param("idOrder") Integer id);

    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") int idCustomer, @Param("method") String method);
}
