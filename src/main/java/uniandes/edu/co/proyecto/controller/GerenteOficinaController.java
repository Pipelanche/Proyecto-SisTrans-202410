package uniandes.edu.co.proyecto.controller;

import uniandes.edu.co.proyecto.modelo.GerenteOficina;
import uniandes.edu.co.proyecto.repositorios.GerenteOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerentesOficina")
public class GerenteOficinaController {

    @Autowired
    private GerenteOficinaRepository gerenteOficinaRepository;

    @PostMapping
    public GerenteOficina createGerenteOficina(@RequestBody GerenteOficina gerenteOficina) {
        return gerenteOficinaRepository.save(gerenteOficina);
    }

}
