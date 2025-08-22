package io.camunda.pulsar.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
import jakarta.validation.constraints.NotEmpty;

public record MyPulsarConnectorRequest(
        @NotEmpty @TemplateProperty(group = "compose", type = PropertyType.Text)
        String url,
        String Topic,
        String message) {}
