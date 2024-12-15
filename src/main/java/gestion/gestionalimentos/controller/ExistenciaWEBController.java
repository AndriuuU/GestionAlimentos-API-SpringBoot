package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.service.ExistenciaService;
import gestion.gestionalimentos.service.AlimentoService;
import gestion.gestionalimentos.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/existencias")
public class ExistenciaWEBController {

    @Autowired
    private ExistenciaService existenciaService;

    @Autowired
    private AlimentoService alimentoService;

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public String listarExistencias(Model model) {
        List<Existencia> existencias = existenciaService.getAllExistencias();
        model.addAttribute("existencias", existencias);
        return "lista-existencias";
    }

    @GetMapping("/nueva")
    public String nuevaExistencia(Model model) {
        model.addAttribute("existencia", new Existencia());
        model.addAttribute("alimentos", alimentoService.getAllAlimentos());
        model.addAttribute("ubicaciones", ubicacionService.getAllUbicaciones());
        return "formulario-existencia";
    }

    @PostMapping("/guardar")
    public String guardarExistencia(@ModelAttribute("existencia") Existencia existencia) {
        existenciaService.saveExistencia(existencia);
        return "redirect:/existencias";
    }

    @GetMapping("/editar/{id}")
    public String editarExistencia(@PathVariable Long id, Model model) {
        Optional<Existencia> existencia = existenciaService.getExistenciaById(id);
        model.addAttribute("existencia", existencia);
        model.addAttribute("alimentos", alimentoService.getAllAlimentos());
        model.addAttribute("ubicaciones", ubicacionService.getAllUbicaciones());
        return "formulario-existencia";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarExistencia(@PathVariable Long id) {
        existenciaService.deleteExistencia(id);
        return "redirect:/existencias";
    }
}
