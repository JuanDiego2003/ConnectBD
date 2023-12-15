package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GamesDAO {
    public static List<EntidadPadre> ConsultarGames(EntidadPadre entidadPadre, Connection connection) {
        List<EntidadPadre> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM juegos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    entidadPadre.getGames().setId_Game(resultado.getInt("id_juego"));
                    entidadPadre.getGames().setTitulo(resultado.getString("titulo"));
                    entidadPadre.getGames().setFecha_Lanzamiento(resultado.getDate("fecha_lanzamiento"));
                    entidadPadre.getGames().setCalificacion(resultado.getDouble("calificacion"));
                    entidadPadre.getGames().setVeces_Listado(resultado.getInt("veces_listado"));
                    entidadPadre.getGames().setNum_Resenas(resultado.getInt("num_resenas"));
                    entidadPadre.getGames().setResumen(resultado.getString("resumen"));
                    entidadPadre.getGames().setNum_Reproducciones(resultado.getInt("num_reproducciones"));
                    entidadPadre.getGames().setNum_Jugando(resultado.getInt("num_jugando"));
                    entidadPadre.getGames().setNum_Atrasos(resultado.getInt("num_atrasos"));
                    entidadPadre.getGames().setNum_Lista_Deseos(resultado.getInt("num_lista_deseos"));
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

    public static boolean InsertarGames(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO juegos (id_juego,titulo,fecha_lanzamiento,calificacion,veces_listado,num_resenas,resumen,num_reproducciones,num_jugando,num_atrasos,num_lista_deseos) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, entidadPadre.getGames().getId_Game());
            pstmt.setString(2, entidadPadre.getGames().getTitulo());
            pstmt.setDate(3, entidadPadre.getGames().getFecha_Lanzamiento());
            pstmt.setDouble(4, entidadPadre.getGames().getCalificacion());
            pstmt.setInt(5, entidadPadre.getGames().getVeces_Listado());
            pstmt.setInt(6, entidadPadre.getGames().getNum_Resenas());
            pstmt.setString(7, entidadPadre.getGames().getResumen());
            pstmt.setInt(8, entidadPadre.getGames().getNum_Reproducciones());
            pstmt.setInt(9, entidadPadre.getGames().getNum_Jugando());
            pstmt.setInt(10, entidadPadre.getGames().getNum_Atrasos());
            pstmt.setInt(11, entidadPadre.getGames().getNum_Lista_Deseos());
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

    public static void EliminarGames(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM juegos WHERE id_juego = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, entidadPadre.getGames().getId_Game());
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
    public static void ActualizarGames(EntidadPadre entidadPadre, Connection connection) {
        boolean correcto = false;
        String consulta = "UPDATE juegos SET titulo=?,fecha_lanzamiento=?,calificacion=?,veces_listado=?,num_resenas=?,resumen=?,num_reproducciones=?,num_jugando=?,num_atrasos=?,num_lista_deseos=? WHERE id_juego = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, entidadPadre.getGames().getId_Game());
            pstmt.setString(2, entidadPadre.getGames().getTitulo());
            pstmt.setDate(3, entidadPadre.getGames().getFecha_Lanzamiento());
            pstmt.setDouble(4, entidadPadre.getGames().getCalificacion());
            pstmt.setInt(5, entidadPadre.getGames().getVeces_Listado());
            pstmt.setInt(6, entidadPadre.getGames().getNum_Resenas());
            pstmt.setString(7, entidadPadre.getGames().getResumen());
            pstmt.setInt(8, entidadPadre.getGames().getNum_Reproducciones());
            pstmt.setInt(9, entidadPadre.getGames().getNum_Jugando());
            pstmt.setInt(10, entidadPadre.getGames().getNum_Atrasos());
            pstmt.setInt(11, entidadPadre.getGames().getNum_Lista_Deseos());
            //condicion de eliminar
            pstmt.setInt(12, entidadPadre.getGames().getId_Game());


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
