import java.sql.Connection;

import Database.DBFunc;
import Interfaces.ABCProductos;

public class App {
    public static void main(String[] args) throws Exception {
        Connection mysql = DBFunc.ConectarBD("bd_inventario", "root", "Salsa1508");
        System.out.println("Hello, World!");
        ABCProductos uiAbcProductos = new ABCProductos();

        uiAbcProductos.setBdConnection(mysql);
        uiAbcProductos.startUi();

        
    }
}
