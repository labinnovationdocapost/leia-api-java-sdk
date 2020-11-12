package io.leia.builder.params;

import com.google.gson.annotations.SerializedName;

import java.util.Dictionary;
import java.util.HashMap;

public class ConditionalJobParams {
    public static final String SERIALIZED_NAME_CALLBACK_URL = "callback_url";
    @SerializedName(SERIALIZED_NAME_CALLBACK_URL)
    private String callbackUrl;
    private HashMap<String, String> callbackHeaders;
    private HashMap<String, ConditionalJobRuleParams> rules = new HashMap<>();
    private String executeAfterId;


    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public HashMap<String, String> getCallbackHeaders() {
        return callbackHeaders;
    }

    public void setCallbackHeaders(HashMap<String, String> callbackHeaders) {
        this.callbackHeaders = callbackHeaders;
    }

    public HashMap<String, ConditionalJobRuleParams> getRules() {
        return rules;
    }
    public void addRule(String key, ConditionalJobRuleParams rule) {
        rules.put(key, rule);
    }

    public void setRules(HashMap<String, ConditionalJobRuleParams> rules) {
        this.rules = rules;
    }

    public String getExecuteAfterId() {
        return executeAfterId;
    }

    public void setExecuteAfterId(String executeAfterId) {
        this.executeAfterId = executeAfterId;
    }
}
