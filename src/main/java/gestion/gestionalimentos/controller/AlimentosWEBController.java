package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlimentosWEBController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping("/")
    public String home(Model model) {
        return "header.html";
    }

    @GetMapping("/alimentos")
    public String listarAlimentos(Model model) {
        model.addAttribute("alimentos", alimentoService.getAllAlimentos());
        return "lista-alimentos";
    }

}
