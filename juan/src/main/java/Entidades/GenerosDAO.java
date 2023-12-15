package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenerosDAO {
    public static List<EntidadPadre> ConsultarEquipos(EntidadPadre entidadPadre, Connection connection) {
        List<EntidadPadre> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM generos";
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

    public static boolean InsertarGeneros(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO generos (id_genero,genero) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Generos> generoInsertar =new ArrayList<>();
            int id = 0;
            for (Generos genero: entidadPadre.getGeneros()) {
                if (!generoInsertar.contains(genero)){
                    genero.setId_genero(id++);
                    generoInsertar.add(genero);
                }
            }
            for (Generos genero: generoInsertar) {
                pstmt.setInt(1, genero.getId_genero());
                pstmt.setString(2, genero.getGenero());
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

    public static void EliminarGames(Generos genero, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM generos WHERE id_genero = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
                pstmt.setInt(1, genero.getId_genero());
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