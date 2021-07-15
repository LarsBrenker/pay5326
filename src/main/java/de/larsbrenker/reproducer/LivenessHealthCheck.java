package de.larsbrenker.reproducer;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Liveness
@ApplicationScoped
public class LivenessHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        return HealthCheckResponse.named("Liveness")
                .withData("memory used", getMemUsed())
                .status(true).build();
    }

    protected long getMemUsed() {
        return getMemoryMXBean().getHeapMemoryUsage().getUsed();
    }


    protected MemoryMXBean getMemoryMXBean() {
        return ManagementFactory.getMemoryMXBean();
    }

}
