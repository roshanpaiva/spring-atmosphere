package org.matt.roberts.configuration;

import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.context.annotation.*;

/**
 * Java-based Spring configuration.
 */
@Configuration
@Import(WebConfiguration.class)
@ComponentScan(basePackages = "org.matt.roberts", excludeFilters = @ComponentScan.Filter(Configuration.class))
public class ContextConfiguration {

    /**
     * A BroadcasterFactory (injected as a singleton) that enables client code to look up broadcasters.
     * @return
     */
    @Bean
    public BroadcasterFactory broadcasterFactory() {
        return BroadcasterFactory.getDefault();
    }
}
