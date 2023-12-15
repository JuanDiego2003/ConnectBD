package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResenasDAO {
    public static List<EntidadPadre> ConsultarEquipos(EntidadPadre entidadPadre, Connection connection) {
        List<EntidadPadre> Lista=new ArrayList<>();
        String consulta = "SELECT * FROM equipos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Resenas resenas = new Resenas();
                    resenas.setId_Resena(resultado.getInt("id_resenas"));
                    resenas.setResena(resultado.getString("resenas"));
                    entidadPadre.getResenas().add(resenas);
                    Lista.add(entidadPadre);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }
}
