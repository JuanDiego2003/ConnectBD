import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entidades.*;
import com.sun.tools.jconsole.JConsoleContext;

public class App {
    public static File file = new File("games.csv");


    public static void main(String[] args) throws SQLException, ParseException {
        DatosVO datosvo = new DatosVO();
        List<EntidadPadre> list = new ArrayList<>();
        EntidadPadre entPadre = new EntidadPadre();
        InsertarTodosDatos(datosvo, list, true);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Consultar datos");
        System.out.println("2. Insertar datos");
        System.out.println("3. Eliminar datos");
        System.out.println("4. Actualizar datos");

        System.out.print("Ingrese la opción deseada: ");
        String opcion = scanner.nextLine();
        Games games = new Games();
        List<Equipos> listaEquipos = new ArrayList<>();
        List<Generos> listageneros = new ArrayList<>();
        List<Resenas> listaResena = new ArrayList<>();

        switch (opcion.toLowerCase()) {
            case "1":
                System.out.print("Ingrese la tabla a consultar (games, equipos, generos reseñas, todo (filtrado por juegos) ): ");
                String tablaConsulta = scanner.nextLine();
                switch (tablaConsulta.toLowerCase()) {
                    case "games":
                        GamesDAO.ConsultarGames(false, ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        EquiposDAO.ConsultarEquipos(ConnectionDB.getInstance().getConnection(), -1);
                        break;
                    case "generos":
                        GenerosDAO.ConsultarGeneros(ConnectionDB.getInstance().getConnection(), -1);
                        break;
                    case "reseñas":
                        ResenasDAO.ConsultarResenas(ConnectionDB.getInstance().getConnection(), -1);
                        break;
                    case "todo":
                        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
                            for (Games gam : GamesDAO.ConsultarGames(false, connection)) {
                                entPadre.setGames(gam);
                                for (GameEquipos gameEquipo : GameEquipoDAO.ConsultarGameEquipos(connection, entPadre.getGames().getId_Game())) {
                                    entPadre.getGameEquipos().add(gameEquipo);
                                    entPadre.getEquipos().add(EquiposDAO.ConsultarEquipos(connection, gameEquipo.getId_Equipo()).get(0));
                                }
                                for (GameGeneros gen : GameGeneroDAO.ConsultarGameGenero(connection, entPadre.getGames().getId_Game())) {
                                    entPadre.getGameGeneros().add(gen);
                                    entPadre.getGeneros().add(GenerosDAO.ConsultarGeneros(connection, gen.getId_Genero()).get(0));
                                }
                                entPadre.setResenas(ResenasDAO.ConsultarResenas(connection, gam.getId_Game()));
                                list.add(entPadre);
                            }
                        }
                        for (EntidadPadre ent : list) {
                            System.out.println(ent.getGames().getTitulo());
                            for (Equipos equipos : ent.getEquipos()) {
                                System.out.println(equipos.getEquipo());
                            }
                            for (Generos gen : ent.getGeneros()) {
                                System.out.println(gen.getGenero());
                            }
                            for (Resenas rese : ent.getResenas()) {
                                System.out.println(rese.getResena());
                            }
                        }
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "2":
                System.out.print("Ingrese la tabla a insertar (games/equipos/generos/reseñas): ");
                String tablaInsercion = scanner.nextLine();
                switch (tablaInsercion.toLowerCase()) {
                    case "games":
                        ObtenerDatosUsuario.ObtenerDatosGame(games);
                        GamesDAO.InsertarGames(games, ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        ObtenerDatosUsuario.ObtenerDatosEquipos(listaEquipos.get(0));
                        EquiposDAO.InsertarEquipos(listaEquipos, ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        ObtenerDatosUsuario.ObtenerDatosGeneros(listageneros.get(0));
                        GenerosDAO.InsertarGeneros(listageneros, ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ObtenerDatosUsuario.ObtenerDatosResenas(listaResena.get(0));
                        ResenasDAO.InsertarResenas(listaResena, games.getId_Game(), ConnectionDB.getInstance().getConnection());
                        break;
                    case "todo":
                        GamesDAO.InsertarGames(games, ConnectionDB.getInstance().getConnection());
                        EquiposDAO.InsertarEquipos(listaEquipos, ConnectionDB.getInstance().getConnection());
                        GenerosDAO.InsertarGeneros(listageneros, ConnectionDB.getInstance().getConnection());
                        ResenasDAO.InsertarResenas(listaResena, GamesDAO.ConsultarGames(true, ConnectionDB.getInstance().getConnection()).size(), ConnectionDB.getInstance().getConnection());
                        entPadre.setGames(games);
                        entPadre.setEquipos(listaEquipos);
                        entPadre.setGeneros(listageneros);
                        GameEquipoDAO.InsertarGameEquipos(entPadre, ConnectionDB.getInstance().getConnection());
                        GameGeneroDAO.InsertarGameGenero(entPadre, ConnectionDB.getInstance().getConnection());
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "3":
                System.out.print("Ingrese la tabla a eliminar (games/equipos/generos/reseñas): ");
                String tablaEliminacion = scanner.nextLine();
                switch (tablaEliminacion.toLowerCase()) {
                    case "games":
                        GamesDAO.EliminarGames(games, ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        EquiposDAO.EliminarEquipos(listaEquipos.get(0).getId_Equipo(), ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        GenerosDAO.EliminarGeneros(listageneros.get(0).getId_genero(), ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ResenasDAO.EliminarResenas(listaResena.get(0).getId_Game(), ConnectionDB.getInstance().getConnection());
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "4":
                System.out.print("Ingrese la tabla a actualizar (games/equipos/generos/reseñas): ");
                String tablaActualizacion = scanner.nextLine();
                switch (tablaActualizacion.toLowerCase()) {
                    case "games":
                        ObtenerDatosUsuario.ObtenerDatosGame(games);
                        GamesDAO.ActualizarGames(games, ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        ObtenerDatosUsuario.ObtenerDatosEquipos(listaEquipos.get(0));
                        EquiposDAO.ActualizarEquipos(listaEquipos.get(0), ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        ObtenerDatosUsuario.ObtenerDatosGeneros(listageneros.get(0));
                        GenerosDAO.ActualizarGeneros(listageneros.get(0), ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ObtenerDatosUsuario.ObtenerDatosResenas(listaResena.get(0));
                        ResenasDAO.ActualizarResenas(listaResena.get(0), ConnectionDB.getInstance().getConnection());
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            default:
                System.out.println("Opción no válida");
        }
        scanner.close();
    }

    private static void InsertarTodosDatos(DatosVO datosvo, List<EntidadPadre> list, boolean inicial) {
        boolean correcto = true;

        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (inicial) {
                if (GamesDAO.ConsultarGames(true, connection).isEmpty()) {
                    ObtenerDatosGames obtenerDatosGames = new ObtenerDatosGames();
                    list = obtenerDatosGames.leerArchivo();
                    for (EntidadPadre entidad : list) {
                        GamesDAO.InsertarGames(entidad.getGames(), connection);
                        EquiposDAO.InsertarEquipos(entidad.getEquipos(), connection);
                        GenerosDAO.InsertarGeneros(entidad.getGeneros(), connection);
                        ResenasDAO.InsertarResenas(entidad.getResenas(), GamesDAO.ConsultarGames(true, connection).size(), connection);
                        GameEquipoDAO.InsertarGameEquipos(entidad, connection);
                        GameGeneroDAO.InsertarGameGenero(entidad, connection);

                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("No se pudo insertar todos los datos del archivo games");
        }
        System.out.println("Se han insertado todos los datos del archivo games");

        list.clear();
    }
}
