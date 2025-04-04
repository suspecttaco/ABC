package Database;

import java.sql.Connection; //SQL library
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBFunc {
     //Funcion para conectar con una base de datos local
     public static Connection ConectarBD(String bd, String user, String pass){
        Connection conexion;

        String host = "jdbc:mysql://localhost/";

        System.out.println("Conectando....");

        try {
            conexion = DriverManager.getConnection(host + bd, user, pass);
            System.out.println("Conexion exitosa!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);

        }

        return conexion;
    }

    //Funcion para desconectarse de la base de datos
    public static void Desconexion(Connection cb){
        try {
            cb.close();
            System.out.println("Desconectado correctamente!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
}
