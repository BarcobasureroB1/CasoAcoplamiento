package logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderManager {
    // Variable global compartida para el estado de los pedidos
    private static String globalOrderState = "No Orders";

    private Connection connection;

    public OrderManager() {
        connect();
    }

    // Conectar a la base de datos
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:orders.db");
            System.out.println("Database connection established.");
            setupDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Configurar la base de datos con una tabla para pedidos
    private void setupDatabase() {
        try (Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS orders (id INTEGER PRIMARY KEY AUTOINCREMENT, product TEXT, quantity INTEGER)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertar un nuevo pedido
    public void insertOrder(String product, int quantity) {
        String sql = "INSERT INTO orders (product, quantity) VALUES ('" + product + "', " + quantity + ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Order inserted: " + product + ", Quantity: " + quantity);
            globalOrderState = "Order Placed";
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGlobalOrderState(String newState){
        globalOrderState = newState;
    }

    public static String getGlobalOrderState(){
        return globalOrderState;
    }

    public void placeNewOrder() {
        System.out.println("New Order Placement Logic");
    }

    public void createOrder() {
        System.out.println("Order Creation Logic Changed");
    }
}
