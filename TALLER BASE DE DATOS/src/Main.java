import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://bbjvuorblnepnr06thuw-mysql.services.clever-cloud.com:3306/bbjvuorblnepnr06thuw";
        String user = "ur7quvbcyib45yaz";
        String password = "zguvsAjoQkpfBCuu9SiB";

        // Crear un objeto Scanner para leer los datos de la consola
        Scanner scanner = new Scanner(System.in);

        // Pedir los datos del nuevo cliente
        System.out.print("Ingrese el ID: ");
        int iddd = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea restante

        System.out.print("Ingrese el USUARIO: ");
        String usuariooo = scanner.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contraseñaaa = scanner.nextLine();

        // Insertar un nuevo cliente
        insertar_usuario(iddd, usuariooo, contraseñaaa, url, user, password);

        // Cerrar el scanner
        scanner.close();

        // Usar try-with-resources para manejar la conexión, el statement y el resultSet
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM usuarios")) {

            System.out.println("¡CONECTADO!");

            // Iterar a través del resultado de la consulta
            while (resultSet.next()) {
                int idd = resultSet.getInt("id");
                String usuarioo = resultSet.getString("usuario");
                String contraseñaa = resultSet.getString("contraseña");

                // Imprimir los resultados
                System.out.println("ID: " + idd + ", Usuario: " + usuarioo + ", Contraseña: " + contraseñaa);
            }

        } catch (SQLException e) {
            // Imprimir la traza del error si ocurre una excepción
            e.printStackTrace();
        }
    }

    public static void insertar_usuario(int iddd, String usuariooo, String contraseñaaa, String url, String user, String password) {
        // Crear la consulta de inserción
        String query = String.format("INSERT INTO usuarios (id, usuario, contraseña) VALUES (%d, '%s', '%s')", iddd, usuariooo, contraseñaaa);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Ejecuta la consulta de inserción
            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Cliente insertado exitosamente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
