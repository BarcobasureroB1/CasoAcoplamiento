package app;

import logica.OrderManager;
import logica.UserManager;

public class Main {
    public static void main(String[] args) {
        // Inicializa OrderManager (Clase B)
        OrderManager orderManager = new OrderManager();

        // Inicializa UserManager con referencia a OrderManager
        UserManager userManager = new UserManager(orderManager);

        // UserManager accede directamente a la base de datos de pedidos
        userManager.directlyAccessDatabase("Laptop", 2);

        // Modificar el estado global de pedidos desde UserManager
        userManager.modifyOrderGlobalState("Urgent Order");

        // Mostrar el estado global modificado
        System.out.println("Global order state is now: " + OrderManager.globalOrderState);

        // Usar métodos inestables de OrderManager
        userManager.useUnstableMethods();

        // Desconectar la base de datos
        orderManager.disconnect();
    }
}
