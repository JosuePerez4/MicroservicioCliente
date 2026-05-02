package org.example.microserviciocliente.Config;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_CLIENTES = "cliente-events-exchange";
    public static final String ROUTING_KEY_CLIENTE_CREADO = "cliente.creado";

    @Bean
    public TopicExchange clienteExchange() {
        return new TopicExchange(EXCHANGE_CLIENTES, true, false);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
