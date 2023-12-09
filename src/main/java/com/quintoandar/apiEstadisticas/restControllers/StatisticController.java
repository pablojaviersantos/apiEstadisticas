package com.quintoandar.apiEstadisticas.restControllers;
import com.quintoandar.apiEstadisticas.services.StatisticServices;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
    private final StatisticServices statisticServices;

    @Autowired
    public StatisticController(StatisticServices statisticServices){

        this.statisticServices = statisticServices;
    }

    @GetMapping("/{label}/{op}/value")
    public HashMap<String, LinkedHashMap<String, Float>> getStatistics(
            @Parameter(description = "Etiqueta a buscar", example = "<all,a,b,c>") @PathVariable String label,
            @Parameter(description = "Operacion a buscar", example = "<all,minimo,maximo,promedio,desvStandar>") @PathVariable String op
    ) {
        return  statisticServices.obtenerEstadisticasPorEtiqueta(label,op);
    }
}