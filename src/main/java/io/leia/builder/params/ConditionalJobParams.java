package io.leia.builder.params;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.leia.client.model.ConditionalBody;

import java.util.Dictionary;
import java.util.HashMap;

public class ConditionalJobParams extends ConditionalBody {
    @Expose(serialize = false)
    private String executeAfterId;

    public ConditionalJobParams() {
        setRules(new HashMap<>());
    }

    public HashMap<String, String> getCallbackHeaders() {
        return (HashMap<String, String>) super.getCallbackHeaders();
    }

    public void setCallbackHeaders(HashMap<String, String> callbackHeaders) {
        super.setCallbackHeaders(callbackHeaders);
    }

    public HashMap<String, ConditionalJobRuleParams> getRules() {
        return (HashMap<String, ConditionalJobRuleParams>) super.getRules();
    }
    public void addRule(String key, ConditionalJobRuleParams rule) {
        getRules().put(key, rule);
    }

    public void setRules(HashMap<String, ConditionalJobRuleParams> rules) {
        super.setRules(rules);
    }

    public String getExecuteAfterId() {
        return executeAfterId;
    }

    public void setExecuteAfterId(String executeAfterId) {
        this.executeAfterId = executeAfterId;
    }
}
