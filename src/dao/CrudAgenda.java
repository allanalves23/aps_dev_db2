package dao;

import bean.Pessoa;
import connection.ParearConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudAgenda {

    public static boolean inserirPessoa(Pessoa pessoa) {
        String SQL = "INSERT INTO agenda (nome, telefone, email, logradouro, numero, complemento,cidade, estado)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstm;
        try {
            pstm = ParearConexao.conectar().prepareStatement(SQL);
            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getTelefone());
            pstm.setString(3, pessoa.getEmail());
            pstm.setString(4, pessoa.getLogradouro());
            pstm.setInt(5, pessoa.getNumero());
            pstm.setString(6, pessoa.getComplemento());
            pstm.setString(7, pessoa.getCidade());
            pstm.setString(8, pessoa.getEstado());

            pstm.execute();
            ParearConexao.conectar().close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static int removerPessoa(String nome, String email, String telefone) {
        listarAgenda(nome, telefone, email);
        String SQL = "DELETE from agenda where id = ?";
        try {
            PreparedStatement pstm = ParearConexao.conectar().prepareStatement(SQL);
            pstm.setInt(1, listarAgenda(nome, telefone, email));
            ParearConexao.conectar().close();
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void listarAgenda() {
        String SQL = "SELECT nome, telefone, email, logradouro, numero, complemento, "
                + "cidade, estado FROM agenda";
        try {
            PreparedStatement pstm = ParearConexao.conectar().prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();
            int registros = 0;
            System.out.println("Listando...");
            while (rs.next()) {
                System.out.print(rs.getString("nome") + " - ");
                System.out.print(rs.getString("telefone") + " - ");
                System.out.print(rs.getString("email") + " - ");
                System.out.print(rs.getString("logradouro") + " - ");
                System.out.print(rs.getInt("numero") + " - ");
                System.out.print(rs.getString("complemento") + " - ");
                System.out.print(rs.getString("cidade") + " - ");
                System.out.println(rs.getString("estado"));
                registros++;
            }
            if(registros == 0){
                System.out.println("Nenhum registro encontrado!");
            }else{
                System.out.println("Fim da listagem!");
            }
            ParearConexao.conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarAgenda(String nome) {
        String SQL = "SELECT nome, telefone, email, logradouro, numero, complemento, "
                + "cidade, estado FROM agenda WHERE nome = ?";
        try {
            PreparedStatement pstm = ParearConexao.conectar().prepareStatement(SQL);
            pstm.setString(1, nome);
            ResultSet rs = pstm.executeQuery();
            int registros = 0;
            System.out.println("Processando...");
            while (rs.next()) {
                System.out.print(rs.getString("nome") + " - ");
                System.out.print(rs.getString("telefone") + " - ");
                System.out.print(rs.getString("email") + " - ");
                System.out.print(rs.getString("logradouro") + " - ");
                System.out.print(rs.getInt("numero") + " - ");
                System.out.print(rs.getString("complemento") + " - ");
                System.out.print(rs.getString("cidade") + " - ");
                System.out.println(rs.getString("estado"));
                registros++;
            }
            if(registros == 0){
                System.out.println("Nenhum registro encontrado!");
            }else{
                System.out.println("Fim da listagem!");
            }
            ParearConexao.conectar().close();
        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int listarAgenda(String nome, String telefone, String email) {
        String SQL = "SELECT id FROM agenda WHERE nome = ? and telefone = ? and email = ?";
        try {
            PreparedStatement pstm = ParearConexao.conectar().prepareStatement(SQL);
            pstm.setString(1, nome);
            pstm.setString(2, telefone);
            pstm.setString(3, email);

            ResultSet rs = pstm.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            
            ParearConexao.conectar().close();
            
            if (id != 0) {
                return id;
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static int atualizarCadastro(String valorantigo, String valornovo, int option) {
        String SQL = "";
        switch (option) {
            case 1: {
                SQL = "UPDATE agenda set nome = ? where nome = ?";
                break;
            }
            case 2: {
                SQL = "UPDATE agenda set telefone = ? where telefone = ?";
                break;
            }
            case 3: {
                SQL = "UPDATE agenda set email = ? where email = ?";
                break;
            }
        }

        try {
            PreparedStatement pstm = ParearConexao.conectar().prepareStatement(SQL);
            pstm.setString(1, valornovo);
            pstm.setString(2, valorantigo);
            int res = pstm.executeUpdate();
            ParearConexao.conectar().close();
            return res;

        } catch (SQLException ex) {
            Logger.getLogger(CrudAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
