import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.*;
import com.sun.tools.jconsole.JConsoleContext;

public class App {
    public static File file = new File("games.csv");


    public static void main(String[] args) throws SQLException {
        DatosVO datosvo = new DatosVO();
        List<EntidadPadre> list = new ArrayList<>();
        System.out.println("Menu:");
        EntidadPadre entPadre=new EntidadPadre();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            for (Games gam:GamesDAO.ConsultarGames(false,connection)) {
                entPadre.setGames(gam);
            for(GameEquipos gameEquipo: GameEquipoDAO.ConsultarGameEquipos(connection,entPadre.getGames().getId_Game())){
                entPadre.getGameEquipos().add(gameEquipo);
                entPadre.getEquipos().add(EquiposDAO.ConsultarEquipos(connection, gameEquipo.getId_Equipo()).get(0));
            }
            for (GameGeneros gen: GameGeneroDAO.ConsultarGameGenero(connection,entPadre.getGames().getId_Game())) {
                entPadre.getGameGeneros().add(gen);
                entPadre.getGeneros().add(GenerosDAO.ConsultarGeneros(connection, gen.getId_Genero()).get(0));
            }
            entPadre.setResenas(ResenasDAO.ConsultarResenas(connection,gam.getId_Game()));
            list.add(entPadre);
            }
        }
        for (EntidadPadre ent: list) {
            System.out.println(ent.getGames().getTitulo());
            for (Equipos equipos: ent.getEquipos()){
                System.out.println(equipos.getEquipo());
            }
            for (Generos gen: ent.getGeneros()) {
                System.out.println(gen.getGenero());
            }
            for (Resenas rese: ent.getResenas()) {
                System.out.println(rese.getResena());
            }
        }
        System.out.println("1. Consultar datos");
        System.out.println("2. Actualizar datos");
        System.out.println("3. Eliminar datos");
        EntidadPadre entidadPadre= new EntidadPadre();
        switch ("") {
            case "consultar":
                InsertarTodosDatos( list, true);
                break;
            case "insertar":
                break;
            case "eliminar":
                break;
            case "actualizar":
                break;
        }
    }
        private static void InsertarTodosDatos(List<EntidadPadre> list, boolean inicial) {
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {
            if (inicial) {
                if (GamesDAO.ConsultarGames(true,connection).isEmpty()) {
                    ObtenerDatosGames obtenerDatosGames = new ObtenerDatosGames();
                    list = obtenerDatosGames.leerArchivo();
                    boolean correcto = true;
                    List<Equipos> as = new ArrayList<>();
                    as = (EquiposDAO.ConsultarEquipos(connection,-1));
                    for (EntidadPadre entidad : list) {
                        GamesDAO.InsertarGames(entidad, connection);
                        EquiposDAO.InsertarEquipos(entidad.getEquipos(),connection);
                        GenerosDAO.InsertarGeneros(entidad.getGeneros(),connection);
                        ResenasDAO.InsertarResenas(entidad.getResenas(),GamesDAO.ConsultarGames(true,connection).size(),connection);
                        GameEquipoDAO.InsertarGameEquipos(entidad,connection);
                        GameGeneroDAO.InsertarGameGenero(entidad,connection);
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
        list.clear();
    }
}
