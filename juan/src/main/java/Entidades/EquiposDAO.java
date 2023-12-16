package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquiposDAO {
    public static List<Equipos> ConsultarEquipos(Connection connection) {
        List<Equipos> Lista = new ArrayList<>();
        String consulta = "SELECT * FROM equipos";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            try (ResultSet resultado = pstmt.executeQuery()) {
                while (resultado.next()) {
                    Equipos equipo = new Equipos();
                    equipo.setId_Equipo(resultado.getInt("id_equipo"));
                    equipo.setEquipo(resultado.getString("equipo"));
                    Lista.add(equipo);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Lista;
    }

    public static boolean InsertarEquipos(List<Equipos> equipos, Connection connection) {
        Equipos falla = new Equipos();
        boolean correcto = false;
        int filasAfectadas = 0;
        String consulta = "INSERT INTO equipos (id_equipo,equipo) VALUES(?,?)";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            List<Equipos> compararExistencia = ConsultarEquipos(connection);
            int id = compararExistencia.size();
            for (Equipos equipo : equipos) {
                boolean repetido = false;
                falla.setEquipo(equipo.getEquipo());
                if (equipo.getEquipo() != null) {
                    if (!compararExistencia.isEmpty()) {
                        for (Equipos equip : compararExistencia) {
                            if (equip.getEquipo().replaceAll(" ","").equalsIgnoreCase(equipo.getEquipo().replaceAll(" ",""))) {
                                repetido = true;
                            }
                        }
                    }
                    if (!repetido) {
                        id++;
                        pstmt.setInt(1, id);
                        if (equipo.getEquipo() == null) {
                            equipo.setEquipo("");
                        }
                        pstmt.setString(2, equipo.getEquipo());
                        filasAfectadas = pstmt.executeUpdate();
                    }
                    System.out.println(falla.getEquipo());
                }else{
                    System.out.println("hola");
                }

            }
            correcto = true;

        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (filasAfectadas > 0) {
            correcto = true;
        }
        return correcto;
    }

    public static void EliminarEquipos(Equipos equipo, Connection connection) {
        boolean correcto = false;
        String consulta = "DELETE FROM equipos WHERE id_equipo = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(consulta)) {
            pstmt.setInt(1, equipo.getId_Equipo());
            pstmt.executeUpdate();
            correcto = true;
            pstmt.executeUpdate();
        } catch (SQLException e) {
            correcto = false;
            throw new RuntimeException(e);
        }
        if (correcto) {
            System.out.println("El registro se ha insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el registro.");
        }
    }

}
