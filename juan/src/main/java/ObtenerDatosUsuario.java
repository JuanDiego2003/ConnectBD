import Entidades.Equipos;
import Entidades.Games;
import Entidades.Generos;
import Entidades.Resenas;

import java.sql.Date;
import java.text.ParseException;
import java.util.Scanner;

public class ObtenerDatosUsuario {
    static Scanner sc = new Scanner(System.in);

    public static void ObtenerDatosGame(Games game) throws ParseException {
        System.out.println("Introduce el titulo del juego");
        game.setTitulo(sc.nextLine());

        System.out.println("Introduce la fecha de lanzamiento (formato dd/MM/yyyy)");
        game.setFecha_Lanzamiento( Formato.formatoFecha(sc.nextLine()));

        double calificacion = 0.0;
        while (true) {
            System.out.println("Introduce la calificacion");
            if (sc.hasNextDouble()) {
                calificacion = sc.nextDouble();
                break;
            } else {
                System.out.println("Entrada no válida. Introduce un número.");
                sc.next();
            }
        }
        game.setCalificacion(calificacion);
        int veces_Listado = 0;
        while (true) {
            System.out.println("Introduce las veces listado");
            if (sc.hasNextInt()) {
                veces_Listado = sc.nextInt();
                break;
            } else {
                System.out.println("Entrada no válida. Introduce un número entero.");
                sc.next();
            }
        }
        game.setVeces_Listado(veces_Listado);
        int num_Resenas = 0;
        while (true) {
            System.out.println("Introduce el numero de resenas");
            if (sc.hasNextInt()) {
                num_Resenas = sc.nextInt();
                break;
            } else {
                System.out.println("Entrada no válida. Introduce un número entero.");
                sc.next();
            }
        }
        game.setNum_Resenas(num_Resenas);
        System.out.println("Introduce un resumen del juego");
        sc.nextLine();
        String resumen = sc.nextLine();

        int num_Reproducciones = 0;
        while (true) {
            System.out.println("Introduce el numero de reproducciones");
            if (sc.hasNextInt()) {
                num_Reproducciones = sc.nextInt();
                break;
            } else {
                System.out.println("Entrada no válida. Introduce un número entero.");
                sc.next();  // Limpiar el buffer del escáner
            }
        }
        game.setNum_Reproducciones(num_Reproducciones);


        System.out.println("Introduce el numero en lista de deseos");
        int num_Lista_Deseos = 0;
        while (true) {
            if (sc.hasNextInt()) {
                num_Lista_Deseos = sc.nextInt();
                break;
            } else {
                System.out.println("Entrada no válida. Introduce un número entero.");
                sc.next();
            }
        }
        game.setNum_Lista_Deseos(num_Lista_Deseos);
    }
    public static void ObtenerDatosEquipos(Equipos equipos) throws ParseException {
        System.out.println("Introduce el nombre del equipo");
        sc.nextLine();
        equipos.setEquipo(sc.nextLine());
    }
    public static void ObtenerDatosGeneros(Generos generos) throws ParseException {
        System.out.println("Introduce el genero");
        sc.nextLine();
        generos.setGenero(sc.nextLine());
    }
    public static void ObtenerDatosResenas(Resenas resenas) throws ParseException {
        System.out.println("Introduce el nombre del equipo");
        sc.nextLine();
        resenas.setResena(sc.nextLine());
    }
}
