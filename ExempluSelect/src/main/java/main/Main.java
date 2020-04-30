package main;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/exselect";//protocol=cum accesez resursa, unde e resursa, cine e resursa
        String user = "root";
        String pass = "";
        try(Connection con = DriverManager.getConnection(url,user,pass)){
            String sql = "SELECT * FROM persoane";//obt toate inregistrarile din tabela persoane
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs =stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String nume = rs.getString("nume");
                String prenume = rs.getString("prenume");
                System.out.println(id+" "+nume+" "+prenume);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
