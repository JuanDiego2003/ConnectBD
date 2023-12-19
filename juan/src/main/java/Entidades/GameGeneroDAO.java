package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameGeneroDAO {
    public static List<GameGeneros> ConsultarGameEquipos(Connection connection) {
        List<GameGeneros> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM generos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    GameGeneros genero = new GameGeneros();
                    genero.setId_Game(resultado.getInt("id_genero"));
                    genero.setId_Genero(resultado.getInt("id_genero"));
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
        String consulta = "INSERT INTO juego_genero (id_juego,id_genero) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            for (Generos equip : entidadPadre.getGeneros()) {
                for (Generos generBD : GenerosDAO.ConsultarGeneros(connection)) {
                    pstmt.setInt(1, entidadPadre.getGames().getId_Game());
                    if (equip.getGenero().equalsIgnoreCase(generBD.getGenero())) {
                        pstmt.setInt(2, generBD.getId_genero());
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
