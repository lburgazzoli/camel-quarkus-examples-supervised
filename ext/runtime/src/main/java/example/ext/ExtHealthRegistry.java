package example.ext;

import org.apache.camel.CamelContext;
import org.apache.camel.microprofile.health.ConnectorHealthCheckRegistry;

public class ExtHealthRegistry extends ConnectorHealthCheckRegistry {

    public ExtHealthRegistry(CamelContext camelContext) {
        super(camelContext);
    }
}
