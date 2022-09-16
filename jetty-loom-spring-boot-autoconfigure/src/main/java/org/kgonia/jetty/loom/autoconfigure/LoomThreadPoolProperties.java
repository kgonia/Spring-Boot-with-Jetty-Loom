package org.kgonia.jetty.loom.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "server.jetty.threads.loom",
        ignoreUnknownFields = true
)
public class LoomThreadPoolProperties {

    private boolean inheritInheritableThreadLocals;

    public boolean isInheritInheritableThreadLocals() {
        return inheritInheritableThreadLocals;
    }

    public void setInheritInheritableThreadLocals(boolean inheritInheritableThreadLocals) {
        this.inheritInheritableThreadLocals = inheritInheritableThreadLocals;
    }
}
