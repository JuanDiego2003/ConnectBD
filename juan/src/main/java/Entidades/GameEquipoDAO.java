package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameEquipoDAO {
    public static List<GameEquipos> ConsultarGameEquipos(Connection connection) {
        List<GameEquipos> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM generos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    GameEquipos genero = new GameEquipos();
                    genero.setId_Game(resultado.getInt("id_genero"));
                    genero.setId_Equipo(resultado.getInt("id_equipo"));
                    Lista.add(genero);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }

    public static boolean InsertarGameEquipos(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO juego_equipo (id_juego,id_equipo) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            for (Equipos equip : entidadPadre.getEquipos()) {
                for (Equipos equipBD : EquiposDAO.ConsultarEquipos(connection)) {
                    pstmt.setInt(1, entidadPadre.getGames().getId_Game());
                    if (equip.getEquipo().equalsIgnoreCase(equipBD.getEquipo())) {
                        pstmt.setInt(2, equipBD.getId_Equipo());
                        filasAfectadas = pstmt.executeUpdate();

                    }
                }
            }
            correcto = true;
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (filasAfectadas > 0) {
            correcto = true;
        }
        return correcto;
    }
}
