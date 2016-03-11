package org.eclipse.scout.apps.helloworld.server;

import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.scout.rt.platform.ApplicationScoped;

@ApplicationScoped
public class Counter {
    private final AtomicLong counter = new AtomicLong();

    public Long nextValue() {
    	return counter.incrementAndGet();
    }
}
