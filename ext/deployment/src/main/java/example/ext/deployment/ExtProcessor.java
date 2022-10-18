package example.ext.deployment;

import org.apache.camel.quarkus.core.deployment.spi.CamelContextCustomizerBuildItem;

import example.ext.ExtRecorder;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

public class ExtProcessor {
    private static final String FEATURE = "ext-camel-microprofile-health";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.STATIC_INIT)
    CamelContextCustomizerBuildItem customizeHealthCheckRegistry(ExtRecorder recorder) {
        return new CamelContextCustomizerBuildItem(recorder.bindHealthCheckRegistry());
    }
}
