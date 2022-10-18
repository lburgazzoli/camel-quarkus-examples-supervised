package org.example;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.component.kafka.KafkaHealthCheckRepository;
import org.apache.camel.health.HealthCheckHelper;
import org.apache.camel.health.HealthCheckRegistry;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class Application {
    @Inject
    CamelContext context;

    void onStart(@Observes StartupEvent ev) {
        KafkaHealthCheckRepository repository = HealthCheckHelper.getHealthCheckRepository(
            context,
            "camel-kafka",
            KafkaHealthCheckRepository.class);

        context.getExtension(HealthCheckRegistry.class).register(repository);
    }
}
