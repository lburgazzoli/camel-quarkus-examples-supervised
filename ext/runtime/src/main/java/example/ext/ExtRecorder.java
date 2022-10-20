package example.ext;

import org.apache.camel.CamelContext;
import org.apache.camel.health.HealthCheckRegistry;
import org.apache.camel.spi.CamelContextCustomizer;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class ExtRecorder {
    public RuntimeValue<CamelContextCustomizer> createContextCustomizer() {
        return new RuntimeValue<>(new CamelContextCustomizer() {

            @Override
            public void configure(CamelContext camelContext) {
                configureHealthCheckRegistry(camelContext);
            }

            /*
             * explicitly configure the health check registry as a workaround
             * for https://issues.apache.org/jira/browse/CAMEL-18617
             */
            private void configureHealthCheckRegistry(CamelContext camelContext) {
                HealthCheckRegistry registry = new ExtHealthRegistry(camelContext);
                registry.setId("camel-microprofile-health");
                registry.setEnabled(true);

                camelContext.setExtension(HealthCheckRegistry.class, registry);
            }
        });
    }
}
