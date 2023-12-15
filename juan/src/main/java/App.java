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
        InsertarTodosDatos(datosvo, list, true);
        System.out.println("Menu:");
        System.out.println("1. Consultar datos");
        System.out.println("2. Actualizar datos");
        System.out.println("3. Eliminar datos");
        switch ("") {
            case "consultar":
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
                if (GamesDAO.ConsultarGames(datosvo.getEntidadPadre(), connection).isEmpty()) {
                    ObtenerDatosGames obtenerDatosGames = new ObtenerDatosGames();
                    list = obtenerDatosGames.leerArchivo();
                    boolean correcto = true;
                    for (EntidadPadre entidad : list) {
                        if (!GamesDAO.InsertarGames(entidad, connection)){
                            correcto = false;
                        }
                        GamesDAO.ActualizarGames(entidad,connection);
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
