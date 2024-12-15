package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ubicaciones")
public class UbicacionWEBController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public String listarUbicaciones(Model model) {
        List<Ubicacion> ubicaciones = ubicacionService.getAllUbicaciones();
        model.addAttribute("ubicaciones", ubicaciones);
        return "lista-ubicaciones";
    }

    @GetMapping("/nueva")
    public String nuevaUbicacion(Model model) {
        model.addAttribute("ubicacion", new Ubicacion());
        return "formulario-ubicacion";
    }

    @PostMapping("/guardar")
    public String guardarUbicacion(@ModelAttribute("ubicacion") Ubicacion ubicacion) {
        ubicacionService.saveUbicacion(ubicacion);
        return "redirect:/ubicaciones";
    }

    @GetMapping("/editar/{id}")
    public String editarUbicacion(@PathVariable Long id, Model model) {
        Optional<Ubicacion> ubicacion = ubicacionService.getUbicacionById(id);
        model.addAttribute("ubicacion", ubicacion);
        return "formulario-ubicacion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUbicacion(@PathVariable Long id) {
        ubicacionService.deleteUbicacion(id);
        return "redirect:/ubicaciones";
    }
}
