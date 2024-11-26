package org.example.gestionpartes.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class R {
    public static InputStream getProperties(String name) {
        return getInputStream("configuration/" + name);
    }

    public static URL getUI(String name) {
        return getUrl("ui/" + name);
    }

    public static URL getUrl(String path) {
        return Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(path),
                "Resource not found: " + path);
    }

    public static InputStream getInputStream(String path) {
        InputStream resourceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if (resourceStream == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        return resourceStream;
    }
}
