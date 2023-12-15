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

    public static boolean InsertarEquipos(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO equipos (id_equipo,equipo) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Equipos> equipoInsertar =new ArrayList<>();
            int id = 0;
            for (Equipos equipo: entidadPadre.getEquipos()) {
                if (!equipoInsertar.contains(equipo)){
                    equipo.setId_Equipo(id++);
                    equipoInsertar.add(equipo);
                }
            }
            for (Equipos equipo: equipoInsertar) {
                pstmt.setInt(1, equipo.getId_Equipo());
                pstmt.setString(2, equipo.getEquipo());
            }
            correcto = true;
            filasAfectadas = pstmt.executeUpdate();
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (filasAfectadas > 0) {
            correcto=true;
        }
        return correcto;
    }
    public static void EliminarEquipos(Equipos equipo, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM equipos WHERE id_equipo = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, equipo.getId_Equipo());
            pstmt.executeUpdate();
            correcto = true;
            pstmt.executeUpdate();
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (correcto) {
            System.out.println("El registro se ha insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el registro.");
        }
    }

}
