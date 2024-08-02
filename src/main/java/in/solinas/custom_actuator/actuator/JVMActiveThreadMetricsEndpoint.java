package in.solinas.custom_actuator.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.util.*;

@Endpoint(id="solinas-jvm-thread-active")
@Component
public class JVMActiveThreadMetricsEndpoint {

    /**
     * The below custom metrics endpoint for retrieving the jvm 'active' threads count are
     * followed with the default syntax format Json Request which spring uses in the metrics
     * /actuator/metrics/{} endpoint...
     *
     * @return JVM active thread count
     */
    @ReadOperation
    @Bean
    public Map<String, Object> getActiveJVMThreadCounts() {
        var threadMXBean = ManagementFactory.getThreadMXBean();

        // Get the RUNNABLE state active threads alone for more accurate numbers
        var activeThreadNames = Arrays.stream(threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds()))
                .filter(threadInfo -> threadInfo != null && threadInfo.getThreadState() == Thread.State.RUNNABLE)
                .map(ThreadInfo::getThreadName)
                .toList();

        var activeThreadCount = activeThreadNames.size();

        Map<String, Object> activeThreadResponse = new HashMap<>();
        activeThreadResponse.put("name", "solinas.jvm.threads.active");
        activeThreadResponse.put("description", "The current number of active threads in the JVM");

        var measurements = new ArrayList<>();
        var measurement = new HashMap<>();
        measurement.put("activeThreadNames", activeThreadNames);
        measurement.put("activeThreadCount", activeThreadCount);

        measurements.add(measurement);

        activeThreadResponse.put("measurements", measurements);

        return activeThreadResponse;
    }
}
