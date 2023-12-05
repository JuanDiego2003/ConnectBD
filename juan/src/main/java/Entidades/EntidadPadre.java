package Entidades;

import java.util.ArrayList;
import java.util.List;

public class EntidadPadre {
    private Games games = new Games();
    private List<Resenas> resenas = new ArrayList<>();
    private List<Equipos> equipos = new ArrayList<>();
    private List<Generos> generos = new ArrayList<>();

    public Games getGames() {
        return games;
    }

    public void setGames(Games games) {
        this.games = games;
    }

    public List<Resenas> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resenas> resenas) {
        this.resenas = resenas;
    }

    public List<Equipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipos> equipos) {
        this.equipos = equipos;
    }

    public List<Generos> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Generos> generos) {
        this.generos = generos;
    }
}
