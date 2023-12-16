package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenerosDAO {
    public static List<Generos> ConsultarGeneros(Connection connection) {
        List<Generos> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM generos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Generos genero = new Generos();
                    genero.setId_genero(resultado.getInt("id_genero"));
                    genero.setGenero(resultado.getString("genero"));
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

    public static boolean InsertarGeneros(List<Generos> equipos, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO generos (id_genero,genero) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Generos> compararExistencia = ConsultarGeneros(connection);
            int id = compararExistencia.size();
            for (Generos genero : equipos) {
                boolean repetido = false;
                if (genero.getGenero() != null && !genero.getGenero().equalsIgnoreCase("")&&!compararExistencia.isEmpty()) {
                        for (Generos equip : compararExistencia) {
                            if (equip.getGenero().replaceAll(" ", "").equalsIgnoreCase(genero.getGenero().replaceAll(" ", ""))) {
                                repetido = true;
                            }
                        }
                    if (!repetido) {
                        id++;
                        pstmt.setInt(1, id);
                        pstmt.setString(2, genero.getGenero());
                        filasAfectadas = pstmt.executeUpdate();
                        correcto = true;
                    }
                }
            }
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (filasAfectadas > 0) {
            correcto = true;
        }
        return correcto;
    }

    public static void EliminarGeneros(int id, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM generos WHERE id_genero = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, id);
            if (pstmt.executeUpdate() > 0) {
                correcto = true;
            }
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (correcto) {
            System.out.println("El registro se ha eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el registro.");
        }
    }

    public static List<Generos> ActualizarGeneros(Connection connection) {
        boolean correcto = false;
        List<Generos> ListUpdate = new ArrayList<>();
        String consulta = "UPDATE generos SET id_genero=?,genero=?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Generos genero = new Generos();
                    genero.setId_genero(resultado.getInt("id_genero"));
                    genero.setGenero(resultado.getString("genero"));
                    ListUpdate.add(genero);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ListUpdate;
    }

}