package org.eclipse.scout.apps.helloworld.server.model;

/**
 * POJO greeting class with an id and a content field.
 */
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public String toString() {
		return String.format("[%d, '%s']", id, content);
    }
}
