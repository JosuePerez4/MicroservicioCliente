package org.example.microserviciocliente.Service;


import org.example.microserviciocliente.Config.RabbitMQConfig;
import org.example.microserviciocliente.DTO.ClienteCreadoEvent;
import org.example.microserviciocliente.Entity.Cliente;
import org.example.microserviciocliente.Repository.ClienteRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ClienteService {
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Cliente crearCliente(Cliente clienteData) {
        if (clienteData.getEmail() == null || !EMAIL_REGEX.matcher(clienteData.getEmail()).matches()) {
            throw new ResponseStatusException(BAD_REQUEST, "El formato del email no es valido");
        }

        Cliente clienteGuardado = clienteRepository.save(clienteData);

        ClienteCreadoEvent evento = new ClienteCreadoEvent();
        evento.setClienteId(clienteGuardado.getId().toString());
        evento.setNombre(clienteGuardado.getNombre());
        evento.setEmail(clienteGuardado.getEmail());
        evento.setTelefono(clienteGuardado.getTelefono());
        evento.setTimestamp(LocalDateTime.now());
        evento.setEventoId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CLIENTES,
                RabbitMQConfig.ROUTING_KEY_CLIENTE_CREADO,
                evento
        );

        return clienteGuardado;
    }
}