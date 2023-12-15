package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenerosDAO {
    public static List<EntidadPadre> ConsultarEquipos(EntidadPadre entidadPadre, Connection connection) {
        List<EntidadPadre> Lista=new ArrayList<>();
        String consulta = "SELECT * FROM equipos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Generos genero = new Generos();
                    genero.setId_genero(resultado.getInt("id_genero"));
                    genero.setGenero(resultado.getString("genero"));
                    entidadPadre.getGeneros().add(genero);
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
