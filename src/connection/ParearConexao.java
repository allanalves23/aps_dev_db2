package connection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParearConexao {
    
    private static Connection connection;
    
    public static Connection conectar(){
        try {
            Class.forName ("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1/aps_dev_db","root","");
            return connection;
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver JDBC não instalado na aplicação, contate o desenvolvedor - classe: "+ex);
        } catch (SQLException ex) {
            System.out.println("Erro ao adquirir a conexão com o banco, contate o desenvolvedor - classe: "+ex);
        }
        return null;
    }
    
    public static void fechar(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParearConexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
