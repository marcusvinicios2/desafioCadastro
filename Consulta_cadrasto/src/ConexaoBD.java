import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=cadastro_pets;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "app_pets";
    private static final String SENHA = System.getenv("DB_SENHA"); // a mesma do passo 1

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}