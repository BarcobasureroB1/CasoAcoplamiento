package logica;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserManager {
    private OrderManager orderManager;

    public UserManager(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void directlyAccessDatabase(String product, int quantity) {
        orderManager.insertOrder(product, quantity);
    }

    public void modifyOrderGlobalState(String newState) {
        orderManager.setGlobalOrderState(newState);
        System.out.println("UserManager modified global order state to: " + newState);
    }

    public void useUnstableMethods() {
        orderManager.placeNewOrder();
        orderManager.createOrder();
    }
}
