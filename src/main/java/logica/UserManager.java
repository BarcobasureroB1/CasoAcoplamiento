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
        Connection conn = orderManager.getConnection();
        String sql = "INSERT INTO orders (product, quantity) VALUES ('" + product + "', " + quantity + ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Direct order insertion by UserManager: " + product + ", Quantity: " + quantity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyOrderGlobalState(String newState) {
        OrderManager.globalOrderState = newState;
        System.out.println("UserManager modified global order state to: " + newState);
    }

    public void useUnstableMethods() {
        orderManager.placeNewOrder();
        orderManager.createOrder();
    }
}
