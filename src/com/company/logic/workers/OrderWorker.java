package com.company.logic.workers;

import com.company.database.MySqlOrdersHandler;
import com.company.model.Order;
import com.company.transport.response.Response;

public class OrderWorker {
    public static Response addNewOrder(Order order) {
        boolean isSuccess = MySqlOrdersHandler.insertOrder(order.getCourse(), order.getStudent().getUsername());
        return new Response(isSuccess,null);
    }

    public static Response deleteOrder(Order order) {
        boolean isSuccess = MySqlOrdersHandler.deleteOrder(order.getCourse(), order.getStudent().getUsername());
        return new Response(isSuccess,null);
    }
}
