package uniandes.edu.co.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {

    @GetMapping("/administrador")
    public String administrador() {
        return "administrador";
    }

    @GetMapping("/gerente")
    public String gerente() {
        return "gerente";
    }

    @GetMapping("/cliente")
    public String cliente() {
        return "cliente";
    }

    @GetMapping("/cajero")
    public String cajero() {
        return "cajero";
    }
}




