package Entidades;

import java.util.ArrayList;
import java.util.List;

public class EntidadPadre {
    private Games games = new Games();
    private List<Resenas> resenas = new ArrayList<>();
    private List<Equipos> equipos = new ArrayList<>();
    private List<Generos> generos = new ArrayList<>();

    private List<GameEquipos> GameEquipos = new ArrayList<>();
    private List<GameGeneros> GameGeneros = new ArrayList<>();

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

    public List<Entidades.GameEquipos> getGameEquipos() {
        return GameEquipos;
    }

    public void setGameEquipos(List<Entidades.GameEquipos> gameEquipos) {
        GameEquipos = gameEquipos;
    }

    public List<Entidades.GameGeneros> getGameGeneros() {
        return GameGeneros;
    }

    public void setGameGeneros(List<Entidades.GameGeneros> gameGeneros) {
        GameGeneros = gameGeneros;
    }
}
