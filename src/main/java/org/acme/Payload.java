package org.acme;

import java.util.Map;

public record Payload(Map<String, Object> context, String expr) {
    // empty
}