package com.sungjun.api.util;

import org.slf4j.MDC;

public class BaseMethods {

    public static String getTraceId() {
        return MDC.get("traceId");
    }

    public static String getSpanId() {

        return MDC.get("spanId");
    }
}
