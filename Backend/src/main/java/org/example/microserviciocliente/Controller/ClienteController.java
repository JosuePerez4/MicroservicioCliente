package org.example.microserviciocliente.Controller;



import org.example.microserviciocliente.Entity.Cliente;
import org.example.microserviciocliente.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}