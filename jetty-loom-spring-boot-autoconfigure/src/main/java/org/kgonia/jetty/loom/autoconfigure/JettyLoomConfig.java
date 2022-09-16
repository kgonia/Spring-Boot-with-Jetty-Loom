package org.kgonia.jetty.loom.autoconfigure;

import org.eclipse.jetty.loom.infrastructure.LoomThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "server.jetty.threads.loom", havingValue = "true")
@ConditionalOnClass(JettyServletWebServerFactory.class)
public class JettyLoomConfig implements WebServerFactoryCustomizer<JettyServletWebServerFactory> {

    @Autowired
    public JettyLoomConfig(LoomThreadPoolProperties loomThreadPoolProperties) {
        this.loomThreadPoolProperties = loomThreadPoolProperties;
    }

    private final LoomThreadPoolProperties loomThreadPoolProperties;

    @Override
    public void customize(JettyServletWebServerFactory factory) {
        factory.setThreadPool(new LoomThreadPool(loomThreadPoolProperties.isInheritInheritableThreadLocals()));
    }
}
