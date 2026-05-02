package org.example.microserviciocliente.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ClienteCreadoEvent {

    @JsonProperty("cliente_id")
    private String clienteId;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDateTime timestamp;
    @JsonProperty("evento_id")
    private String eventoId;

    public ClienteCreadoEvent() {}

    public String getClienteId() { return clienteId; }
    public void setClienteId(String clienteId) { this.clienteId = clienteId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getEventoId() { return eventoId; }
    public void setEventoId(String eventoId) { this.eventoId = eventoId; }
}
