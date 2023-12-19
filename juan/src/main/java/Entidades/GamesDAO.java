package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GamesDAO {
    public static List<Games> ConsultarGames(boolean inicial, Connection connection) {
        List<Games> Lista = new ArrayList<>();
        String consulta = "";
        if (inicial == true) {
            consulta = "SELECT * FROM juegos";
        } else {
            consulta = ConstruirQuery();
        }
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Games games = new Games();
                    games.setId_Game(resultado.getInt("id_juego"));
                    games.setTitulo(resultado.getString("titulo"));
                    games.setFecha_Lanzamiento(resultado.getDate("fecha_lanzamiento"));
                    games.setCalificacion(resultado.getDouble("calificacion"));
                    games.setVeces_Listado(resultado.getInt("veces_listado"));
                    games.setNum_Resenas(resultado.getInt("num_resenas"));
                    games.setResumen(resultado.getString("resumen"));
                    games.setNum_Reproducciones(resultado.getInt("num_reproducciones"));
                    games.setNum_Jugando(resultado.getInt("num_jugando"));
                    games.setNum_Atrasos(resultado.getInt("num_atrasos"));
                    games.setNum_Lista_Deseos(resultado.getInt("num_lista_deseos"));
                    Lista.add(games);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }

    public static boolean InsertarGames(Games games, Connection connection) {
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO juegos (id_juego,titulo,fecha_lanzamiento,calificacion,veces_listado,num_resenas,resumen,num_reproducciones,num_jugando,num_atrasos,num_lista_deseos) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, games.getId_Game());
            pstmt.setString(2, games.getTitulo());
            pstmt.setDate(3, games.getFecha_Lanzamiento());
            pstmt.setDouble(4, games.getCalificacion());
            pstmt.setInt(5, games.getVeces_Listado());
            pstmt.setInt(6, games.getNum_Resenas());
            pstmt.setString(7, games.getResumen());
            pstmt.setInt(8, games.getNum_Reproducciones());
            pstmt.setInt(9, games.getNum_Jugando());
            pstmt.setInt(10, games.getNum_Atrasos());
            pstmt.setInt(11, games.getNum_Lista_Deseos());
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

    public static void EliminarGames(Games game, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM juegos WHERE id_juego = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, game.getId_Game());
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

    public static void ActualizarGames(Games games, Connection connection) {
        boolean correcto = false;
        String consulta = "UPDATE juegos SET titulo=?,fecha_lanzamiento=?,calificacion=?,veces_listado=?,num_resenas=?,resumen=?,num_reproducciones=?,num_jugando=?,num_atrasos=?,num_lista_deseos=? WHERE id_juego = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setString(1, games.getTitulo());
            pstmt.setDate(2, games.getFecha_Lanzamiento());
            pstmt.setDouble(3, games.getCalificacion());
            pstmt.setInt(4, games.getVeces_Listado());
            pstmt.setInt(5, games.getNum_Resenas());
            pstmt.setString(6, games.getResumen());
            pstmt.setInt(7, games.getNum_Reproducciones());
            pstmt.setInt(8, games.getNum_Jugando());
            pstmt.setInt(9, games.getNum_Atrasos());
            pstmt.setInt(10, games.getNum_Lista_Deseos());
            //condicion de eliminar
            pstmt.setInt(11, games.getId_Game());


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

    public static String ConstruirQuery() {
        String query = "Select * from juegos where ";
        System.out.println("Como quieres filtrar");
        System.out.println("1.Id");
        System.out.println("2.Titulo");
        System.out.println("3.Todo");
        Scanner sc = new Scanner(System.in);
        boolean valido = true;
        do {
            String cond = "";
            switch (sc.nextLine()) {
                case "1":
                    do {
                        System.out.println("Introduce un numero d id");
                        cond = sc.nextLine();
                    }while (cond.isEmpty());
                    query += "id_juego = "+cond;
                    break;
                case "2":
                    do {
                        System.out.println("Introduce un titulo de juego");
                        cond = sc.nextLine();
                    }while (cond.isEmpty());
                    query += "titulo like '%"+cond+"%'";
                    break;
                case "3":
                    query = "SELECT * FROM equipos";
                    break;
                default:
                    valido = false;
                    System.out.println("Escoge una opcion valida");
                    break;
            }
        } while (!valido);
        return query;
    }
}
