package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ReservaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Date;

@Controller
public class ApiController {

    @Inject
    private ReservaService reservaService;

    @RequestMapping(value = "api/lugaresdisponibles", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody
    String obtenerLugaresDisponibles(@RequestParam("restaurant_id") Long id,
                                     @RequestParam("fecha") Long fecha) {

        Integer respuesta = reservaService.obtenerLugaresDisponiblesParaUnHorario(id, new Date(fecha));
        return "{\"lugares_disponibles\":" + respuesta + "}";
    }
}
