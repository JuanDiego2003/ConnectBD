import Entidades.EntidadPadre;
import Entidades.Equipos;
import Entidades.Generos;
import Entidades.Resenas;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ObtenerDatosGames {
    public void leerArchivo(DatosVO datosvo) {
        File file = new File("games.csv");
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.toURI()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)

        ) {
            for (CSVRecord csvRecord : csvParser) {
                EntidadPadre entidadPadre = new EntidadPadre();
                if (!csvRecord.get(0).isEmpty()) {
                    entidadPadre.getGames().setId_Game(Integer.parseInt(csvRecord.get(0) + 1));
                    entidadPadre.getGames().setTitulo(csvRecord.get(1));
                    entidadPadre.getGames().setFecha_Lanzamiento(Formato.formatoFecha(csvRecord.get(2)));
                    if (csvRecord.get(4).isEmpty()) {
                        entidadPadre.getGames().setCalificacion(0.0);
                    } else {
                        entidadPadre.getGames().setCalificacion(Double.parseDouble(csvRecord.get(4)));
                    }
                    entidadPadre.getGames().setVeces_Listado(Formato.formatoNumero(csvRecord.get(5)));
                    entidadPadre.getGames().setNum_Resenas(Formato.formatoNumero(csvRecord.get(6)));
                    entidadPadre.getGames().setResumen(csvRecord.get(8));
                    entidadPadre.getGames().setNum_Reproducciones(Formato.formatoNumero(csvRecord.get(10)));
                    entidadPadre.getGames().setNum_Jugando(Formato.formatoNumero(csvRecord.get(11)));
                    entidadPadre.getGames().setNum_Atrasos(Formato.formatoNumero(csvRecord.get(12)));
                    entidadPadre.getGames().setNum_Lista_Deseos(Formato.formatoNumero(csvRecord.get(13)));


                    entidadPadre.setEquipos(DatosEquipos(csvRecord.get(3)));
                    entidadPadre.setGeneros(DatosGeneros(csvRecord.get(7)));
                    entidadPadre.setResenas(DatosResenas(csvRecord.get(9)));

                    datosvo.getEntidadPadre().add(entidadPadre);
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Equipos> DatosEquipos(String datos) {
        List<Equipos> lista = new ArrayList<>();
        String[] equipos = Formato.listaString(datos, true);
        for (int i = 0; i < equipos.length; i++) {
            Equipos team = new Equipos();
            team.setId_Equipo(i + 1);
            team.setEquipo(equipos[i]);
            lista.add(team);
        }
        return lista;
    }

    private List<Generos> DatosGeneros(String datos) {
        List<Generos> lista = new ArrayList<>();
        String[] Generos = Formato.listaString(datos, true);
        for (int i = 0; i < Generos.length; i++) {
            Generos gen = new Generos();
            gen.setId_genero(i + 1);
            gen.setGenero(Generos[i]);
            lista.add(gen);
        }
        return lista;
    }

    private List<Resenas> DatosResenas(String datos) {
        List<Resenas> lista = new ArrayList<>();
        String[] resenas = Formato.listaString(datos, true);
        for (int i = 0; i < resenas.length; i++) {
            Resenas rese = new Resenas();
            rese.setId_Resena(i + 1);
            rese.setResena(resenas[i]);
            lista.add(rese);
        }
        return lista;
    }
}
