package com.quintoandar.apiEstadisticas.services;

import com.quintoandar.apiEstadisticas.helpers.Borrar;
import com.quintoandar.apiEstadisticas.helpers.PersistenceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.*;
import java.util.LinkedHashMap;

@Service
public class StatisticServices {

    @Autowired
    private Borrar saludo;

    public LinkedHashMap<String, LinkedHashMap<String, Float>> obtenerEstadisticasPorEtiqueta(String label,String op) {
        LinkedHashMap<String, LinkedHashMap<String, Float>> labeledStatistics = new LinkedHashMap<>();



        saludo.saludar();

        Connection connection = null;
        try {
            // Obtener la conexión
            connection = PersistenceHelper.getConnection();

            // Realizar operaciones en la base de datos (ejemplo: consulta)
        String sql =    "select     e.minimo ,e.maximo ,e.promedio ,e.desv_estandar, e.etiqueta" + "\n" +
                        "from       estadisticas e\n" +
                        "where      e.fecha = (select max(x.fecha) from estadisticas x where ('all'='"+label+"' or etiqueta='"+label+"'))\n" +
                        "           and ('all'='"+label+"' or etiqueta='"+label+"') \n"+
                        "order by etiqueta asc;";

            try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                LinkedHashMap<String,Float> statistics = new LinkedHashMap<>();

                    switch (op){
                        case "minimo":
                            statistics.put("minimo",resultSet.getFloat("minimo"));
                            break;
                        case "maximo":
                            statistics.put("maximo",resultSet.getFloat("maximo"));
                            break;
                        case "promedio":
                            statistics.put("promedio",resultSet.getFloat("promedio"));
                            break;
                        case "desvStandar":
                            statistics.put("desvStandar",resultSet.getFloat("desv_estandar"));
                            break;
                        case "all":
                            statistics.put("minimo",resultSet.getFloat("minimo"));
                            statistics.put("maximo",resultSet.getFloat("maximo"));
                            statistics.put("promedio",resultSet.getFloat("promedio"));
                            statistics.put("desvStandar",resultSet.getFloat("desv_estandar"));
                            break;
                        default:
                            statistics.put("Operacion no validad",-1f);
                            labeledStatistics.put("ERROR",statistics);
                            return labeledStatistics;
                    }
                    labeledStatistics.put(resultSet.getString("etiqueta"),statistics);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            // Cerrar la conexión al finalizar
            PersistenceHelper.closeConnection(connection);
        }
        return labeledStatistics;
    }
}