package io.projeto.api.common.util;

import java.util.UUID;

public class UUIDGenerator {

    public static String nextUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
