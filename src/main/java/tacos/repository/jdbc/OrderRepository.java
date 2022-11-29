package tacos.repository.jdbc;

import tacos.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
