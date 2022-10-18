package example.ext;

import org.apache.camel.CamelContext;
import org.apache.camel.microprofile.health.CamelMicroProfileHealthCheckRegistry;

public class ExtHealthRegistry extends CamelMicroProfileHealthCheckRegistry {

    public ExtHealthRegistry(CamelContext camelContext) {
        super(camelContext);
    }
}
