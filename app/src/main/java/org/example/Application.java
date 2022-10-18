package org.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.component.kafka.KafkaConsumer;
import org.apache.camel.component.kafka.KafkaHealthCheckRepository;
import org.apache.camel.health.HealthCheckHelper;
import org.apache.camel.health.HealthCheckRegistry;
import org.apache.camel.impl.event.RouteStartedEvent;

@ApplicationScoped
public class Application {
    @Inject
    CamelContext context;

    public void onRouteAdd(@Observes RouteStartedEvent event) {
        if (event.getRoute().getConsumer() instanceof KafkaConsumer) {
            KafkaHealthCheckRepository repository = HealthCheckHelper.getHealthCheckRepository(
                context,
                "camel-kafka",
                KafkaHealthCheckRepository.class);

            context.getExtension(HealthCheckRegistry.class).register(repository);
        }
    }
}
