package com.david.demo.errorHandling;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "attribute", "message", "severity"})
public class ErrorTO {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("attribute")
    private String attribute;
    @JsonProperty("severity")
    private ErrorTO.Severity severity;

    @JsonCreator
    public ErrorTO(@JsonProperty("code") String code,
                   @JsonProperty("message") String message,
                   @JsonProperty("attribute")String attribute,
                   @JsonProperty("severity")ErrorTO.Severity severity) {
        this.code = code;
        this.message = message;
        this.attribute = attribute;
        this.severity = severity;
    }

    public ErrorTO(String code, String message, String attribute) {
        this(code, message, attribute, Severity.ERROR);
    }

    public ErrorTO() {
    }

    /**
     * Property getter
     */
    public String getCode() {
        return code;
    }

    /**
     * Property setter
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Property getter
     */
    public String getMessage() {
        return message;
    }

    /**
     * Property setter
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Property getter
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     * Property setter
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     * Property getter
     */
    public Severity getSeverity() {
        return severity;
    }

    /**
     * Property setter
     */
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public static enum Severity {
        ERROR("ERROR"),
        WARN("WARN"),
        INFO("INFO");

        private static final Map<String, ErrorTO.Severity> CONSTANTS = new HashMap();
        private final String value;

        private Severity(String value) {
            this.value = value;
        }

        @JsonCreator
        public static ErrorTO.Severity fromValue(String value) {
            ErrorTO.Severity constant = (ErrorTO.Severity) CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

        @JsonValue
        public String toString() {
            return this.value;
        }

        static {
            ErrorTO.Severity[] var0 = values();
            int var1 = var0.length;

            for (int var2 = 0; var2 < var1; ++var2) {
                ErrorTO.Severity c = var0[var2];
                CONSTANTS.put(c.value, c);
            }

        }
    }
}
