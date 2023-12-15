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
        String consulta = "SELECT * FROM resenas";
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

    public static boolean InsertarResenas(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO equipos (id_equipo,equipo) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Resenas> resenasInsertar = new ArrayList<>();
            int id = 0;
            for (Resenas resena : entidadPadre.getResenas()) {
                if (!resenasInsertar.contains(resena)) {
                    resena.setId_Resena(id++);
                    resenasInsertar.add(resena);
                }
            }
            for (Resenas equipo : resenasInsertar) {
                pstmt.setInt(1, equipo.getId_Resena());
                pstmt.setString(2, equipo.getResena());
            }
            correcto = true;
            filasAfectadas = pstmt.executeUpdate();
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (filasAfectadas > 0) {
            correcto = true;
        }
        return correcto;
    }
    public static void EliminarResenas(Resenas resenas, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM equipos WHERE id_equipo = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, resenas.getId_Resena());
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
