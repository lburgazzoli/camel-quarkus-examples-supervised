package example.ext;

import org.apache.camel.CamelContext;
import org.apache.camel.health.HealthCheckRegistry;
import org.apache.camel.spi.CamelContextCustomizer;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class ExtRecorder {

    public RuntimeValue<CamelContextCustomizer> bindHealthCheckRegistry() {
        return new RuntimeValue<>(new CamelContextCustomizer() {
            @Override
            public void configure(CamelContext camelContext) {
                HealthCheckRegistry registry = new ExtHealthRegistry(camelContext);
                registry.setId("camel-microprofile-health");
                registry.setEnabled(true);

                camelContext.setExtension(HealthCheckRegistry.class, registry);
            }
        });
    }
}
