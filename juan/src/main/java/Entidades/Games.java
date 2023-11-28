package Entidades;

import java.util.Date;

public class Games {
    private int id_Game;
    private String titulo;
    private Date fecha_Lanzamiento;
    private double calificacion;
    private int veces_Listado;
    private int num_Resenas;
    private String resumen;
    private int num_Reproducciones;
    private int num_Jugando;
    private int num_Atrasos;
    private int num_Lista_Deseos;

    public int getId_Game() {
        return id_Game;
    }

    public void setId_Game(int id_Game) {
        this.id_Game = id_Game;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_Lanzamiento() {
        return fecha_Lanzamiento;
    }

    public void setFecha_Lanzamiento(Date fecha_Lanzamiento) {
        this.fecha_Lanzamiento = fecha_Lanzamiento;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getVeces_Listado() {
        return veces_Listado;
    }

    public void setVeces_Listado(int veces_Listado) {
        this.veces_Listado = veces_Listado;
    }

    public int getNum_Resenas() {
        return num_Resenas;
    }

    public void setNum_Resenas(int num_Resenas) {
        this.num_Resenas = num_Resenas;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getNum_Reproducciones() {
        return num_Reproducciones;
    }

    public void setNum_Reproducciones(int num_Reproducciones) {
        this.num_Reproducciones = num_Reproducciones;
    }

    public int getNum_Jugando() {
        return num_Jugando;
    }

    public void setNum_Jugando(int num_Jugando) {
        this.num_Jugando = num_Jugando;
    }

    public int getNum_Atrasos() {
        return num_Atrasos;
    }

    public void setNum_Atrasos(int num_Atrasos) {
        this.num_Atrasos = num_Atrasos;
    }

    public int getNum_Lista_Deseos() {
        return num_Lista_Deseos;
    }

    public void setNum_Lista_Deseos(int num_Lista_Deseos) {
        this.num_Lista_Deseos = num_Lista_Deseos;
    }
}
