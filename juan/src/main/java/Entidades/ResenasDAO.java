package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResenasDAO {
    public static List<Resenas> ConsultarResenas(Connection connection) {
        List<Resenas> lista = new ArrayList<>();
        String consulta = "SELECT * FROM resenas";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Resenas resenas = new Resenas();
                    resenas.setId_Resena(resultado.getInt("id_resena"));
                    resenas.setResena(resultado.getString("resena"));
                    resenas.setId_Game(resultado.getInt("id_juego"));
                    lista.add(resenas);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public static boolean InsertarResenas(List<Resenas> resenas, int idGame, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO resenas (id_resena,resena,id_juego) VALUES(?,?,?)";
        Resenas aux = new Resenas();
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Resenas> compararExistencia = ConsultarResenas(connection);
            int id = compararExistencia.size();
            for (Resenas resena : resenas) {
                aux = resena;
                aux.setId_Game(idGame);
                boolean repetido = false;
                if (resena.getResena() != null && !resena.getResena().equalsIgnoreCase("")) {
                    if (!compararExistencia.isEmpty()) {
                        for (Resenas resen : compararExistencia) {
                            if (resena.getResena().replaceAll(" ", "").equalsIgnoreCase(resen.getResena().replaceAll(" ", ""))) {
                                repetido = true;
                            }
                        }
                    }
                    if (!repetido) {
                        id++;
                        pstmt.setInt(1, id);
                        pstmt.setString(2, resena.getResena());
                        pstmt.setInt(3, idGame);
                        filasAfectadas = pstmt.executeUpdate();
                    }
                }
            }
            System.out.println(aux.getId_Resena() + aux.getResena() + aux.getId_Game());
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

    public static void EliminarResenas(Resenas resenas, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM resenas WHERE id_resena = ?";
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

    public static List<Resenas> ActualizarResenas(Connection connection) {
        List<Resenas> lista = new ArrayList<>();
        String consulta = "UPDATE * FROM resenas";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Resenas resenas = new Resenas();
                    resenas.setId_Resena(resultado.getInt("id_resena"));
                    resenas.setResena(resultado.getString("resena"));
                    resenas.setId_Game(resultado.getInt("id_juego"));
                    lista.add(resenas);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
