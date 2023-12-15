package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquiposDAO {
    public static List<EntidadPadre> ConsultarEquipos(EntidadPadre entidadPadre, Connection connection) {
        List<EntidadPadre> Lista=new ArrayList<>();
        String consulta = "SELECT * FROM equipos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Equipos equipo = new Equipos();
                    equipo.setId_Equipo(resultado.getInt("id_juego"));
                    equipo.setEquipo(resultado.getString("equipo"));
                    entidadPadre.getEquipos().add(equipo);
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
