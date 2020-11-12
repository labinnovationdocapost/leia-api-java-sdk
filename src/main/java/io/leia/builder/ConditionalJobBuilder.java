package io.leia.builder;

import io.leia.builder.params.ConditionalJobParams;
import io.leia.builder.params.ConditionalJobRuleJobParams;
import io.leia.builder.params.ConditionalJobRuleParams;
import io.leia.client.model.Job;

import java.util.HashMap;

public class ConditionalJobBuilder {
    private ConditionalJobParams conditionalJobParams;

    private ConditionalJobBuilder() {
        conditionalJobParams = new ConditionalJobParams();
    }

    public static ConditionalJobBuilder create(String executeAfterId) {
        return new ConditionalJobBuilder().withExecuteAfterId(executeAfterId);
    }
    public static ConditionalJobBuilder create(Job executeAfter) {
        return new ConditionalJobBuilder().withExecuteAfterId(executeAfter.getId());
    }

    private ConditionalJobBuilder withExecuteAfterId(String executeAfterId) {
        conditionalJobParams.setExecuteAfterId(executeAfterId);
        return this;
    }

    public ConditionalJobBuilder withCallback(String callback) {
        conditionalJobParams.setCallbackUrl(callback);
        return this;
    }

    public ConditionalJobBuilder withCallbackHeaders(HashMap<String ,String> callbackHeaders) {
        conditionalJobParams.setCallbackHeaders(callbackHeaders);
        return this;
    }

    public ConditionalJobBuilder addRule(String key, ConditionalJobRuleParams rule) {
        conditionalJobParams.addRule(key, rule);
        return this;
    }

    public ConditionalJobParams build() {
        return conditionalJobParams;
    }
}
