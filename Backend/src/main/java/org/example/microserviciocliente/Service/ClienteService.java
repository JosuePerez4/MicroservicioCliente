package org.example.microserviciocliente.Service;


import org.example.microserviciocliente.DTO.ClienteCreadoEvent;
import org.example.microserviciocliente.Entity.Cliente;
import org.example.microserviciocliente.Repository.ClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Cliente crearCliente(Cliente clienteData) {
        // 1. Guardar en PostgreSQL
        Cliente clienteGuardado = clienteRepository.save(clienteData);

        // 2. Armar el evento asincrónico
        ClienteCreadoEvent evento = new ClienteCreadoEvent();
        evento.setIdCliente(clienteGuardado.getId());
        evento.setNombre(clienteGuardado.getNombre());
        evento.setEmail(clienteGuardado.getEmail());

        // 3. Publicar el mensaje en el exchange
        rabbitTemplate.convertAndSend("cliente.creado.exchange", "", evento);

        return clienteGuardado;
    }
}