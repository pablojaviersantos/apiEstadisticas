package com.quintoandar.apiEstadisticas.restControllers;
import com.quintoandar.apiEstadisticas.helpers.Borrar;
import com.quintoandar.apiEstadisticas.services.StatisticServices;
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
    private Borrar saludo;

    @Autowired
    public StatisticController(StatisticServices statisticServices){

        this.statisticServices = statisticServices;
    }

    @GetMapping("/{label}/{op}/value")
    public HashMap<String, LinkedHashMap<String, Float>> getStatistics(@PathVariable String label, @PathVariable String op) {
        saludo.saludar();
        //saludo.saludar("Pablo desde el Controller\n");
        return  statisticServices.obtenerEstadisticasPorEtiqueta(label,op);
    }
}