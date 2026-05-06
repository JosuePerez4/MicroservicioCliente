package org.example.microserviciocliente.Controller;



import java.util.Map;

import org.example.microserviciocliente.Entity.Cliente;
import org.example.microserviciocliente.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/registro")
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        return Map.of(
                "service", "MicroservicioCliente",
                "status", "UP"
        );
    }
}