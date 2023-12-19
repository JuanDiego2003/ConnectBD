import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        switch ("") {
            case "consultar":
                InsertarTodosDatos(datosvo, list, true);
                break;
            case "insertar":
                break;
            case "eliminar":
                break;
            case "actualizar":
                break;
        }
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
