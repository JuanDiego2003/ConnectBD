import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entidades.*;

public class App {
    public static File file = new File("games.csv");

    
    public static void main(String[] args) throws SQLException {
        DatosVO datosvo = new DatosVO();
        List<EntidadPadre> list = new ArrayList<>();
        System.out.println("Menu:");
        EntidadPadre en=new EntidadPadre();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            System.out.println(GamesDAO.ConsultarGames(false,connection).get(0).getTitulo());
        }
        System.out.println("1. Consultar datos");
        System.out.println("2. Actualizar datos");
        System.out.println("3. Eliminar datos");
        EntidadPadre entidadPadre= new EntidadPadre();
        InsertarTodosDatos(datosvo, list, true);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Consultar datos");
        System.out.println("2. Insertar datos");
        System.out.println("3. Eliminar datos");
        System.out.println("4. Actualizar datos");

        System.out.print("Ingrese la opción deseada: ");
        String opcion = scanner.nextLine();

        switch (opcion.toLowerCase()) {
            case "1":
            case "consultar":
                System.out.print("Ingrese la tabla a consultar (games/equipos): ");
                String tablaConsulta = scanner.nextLine();
                switch (tablaConsulta.toLowerCase()) {
                    case "games":
                        GamesDAO.ConsultarGames(false, ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        EquiposDAO.ConsultarEquipos(ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        GenerosDAO.ConsultarGeneros(ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ResenasDAO.ConsultarResenas(ConnectionDB.getInstance().getConnection());
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "2":
            case "insertar":
                System.out.print("Ingrese la tabla a insertar (games/equipos/generos/reseñas): ");
                String tablaInsercion = scanner.nextLine();
                List<Equipos> listaEquipos = new ArrayList<>();
                List<Generos> listageneros = new ArrayList<>();
                List<Resenas> listaResena = new ArrayList<>();
                switch (tablaInsercion.toLowerCase()) {
                    case "games":
                        GamesDAO.InsertarGames(entidadPadre,ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        EquiposDAO.InsertarEquipos(listaEquipos,ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        GenerosDAO.InsertarGeneros(listageneros,ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ResenasDAO.InsertarResenas(listaResena, ,ConnectionDB.getInstance().getConnection()));
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "3":
            case "eliminar":
                System.out.print("Ingrese la tabla a eliminar (games/equipos/generos/reseñas): ");
                String tablaEliminacion = scanner.nextLine();
                switch (tablaEliminacion.toLowerCase()) {
                    case "games":
                        GamesDAO.EliminarGames(entidadPadre,ConnectionDB.getInstance().getConnection());;
                        break;
                    case "equipos":
                        EquiposDAO.EliminarEquipos( ,ConnectionDB.getInstance().getConnection());;
                        break;
                    case "generos":
                        GenerosDAO.EliminarGeneros( ,ConnectionDB.getInstance().getConnection());;
                        break;
                    case "reseñas":
                        ResenasDAO.EliminarResenas( ,ConnectionDB.getInstance().getConnection());;
                        break;
                    default:
                        System.out.println("Tabla no válida");
                }
                break;
            case "4":
            case "actualizar":
                System.out.print("Ingrese la tabla a actualizar (games/equipos/generos/reseñas): ");
                String tablaActualizacion = scanner.nextLine();
                switch (tablaActualizacion.toLowerCase()) {
                    case "games":
                        GamesDAO.ActualizarGames(entidadPadre,ConnectionDB.getInstance().getConnection());
                        break;
                    case "equipos":
                        EquiposDAO.ActualizarEquipos(ConnectionDB.getInstance().getConnection());
                        break;
                    case "generos":
                        GenerosDAO.ActualizarGeneros(ConnectionDB.getInstance().getConnection());
                        break;
                    case "reseñas":
                        ResenasDAO.ActualizarResenas(ConnectionDB.getInstance().getConnection());
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
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (inicial) {
                if (GamesDAO.ConsultarGames(true,connection).isEmpty()) {
                    ObtenerDatosGames obtenerDatosGames = new ObtenerDatosGames();
                    list = obtenerDatosGames.leerArchivo();
                    boolean correcto = true;
                    List<Equipos> as = new ArrayList<>();
                    as = (EquiposDAO.ConsultarEquipos(connection));
                    for (EntidadPadre entidad : list) {
                        GamesDAO.InsertarGames(entidad, connection);
                        EquiposDAO.InsertarEquipos(entidad.getEquipos(),connection);
                        GenerosDAO.InsertarGeneros(entidad.getGeneros(),connection);
                        ResenasDAO.InsertarResenas(entidad.getResenas(),GamesDAO.ConsultarGames(true,connection).size(),connection);
                        GameEquipoDAO.InsertarGameEquipos(entidad,connection);
                        GameGeneroDAO.InsertarGameEquipos(entidad,connection);
                        correcto = false;
                        //}
                        //GamesDAO.ActualizarGames(entidad,connection);

                    }
                    if (correcto) {
                        System.out.println("Se han insertado todos los datos del archivo games");
                    } else {
                        System.out.println("No se pudo insertar todos los datos del archivo games");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
