package com.benjamin.ecommerce.shared.spring.config;

import brave.handler.MutableSpan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.BytesEncoder;
import zipkin2.reporter.otel.brave.OtlpProtoV1Encoder;

@Configuration(proxyBeanMethods = false)
public class BraveOtlpConfig {

    @Bean
    BytesEncoder<MutableSpan> otlpMutableSpanBytesEncoder() {
        return OtlpProtoV1Encoder.create();
    }

}
