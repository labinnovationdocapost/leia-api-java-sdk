package io.leia.builder;

import io.leia.builder.params.AddModelParams;
import io.leia.builder.params.ConditionalJobRuleJobParams;
import io.leia.builder.params.ConditionalJobRuleParams;
import io.leia.builder.model.ConditionOperatorTypes;
import org.apache.commons.lang3.tuple.Pair;

public class ConditionalJobRuleBuilder {
    private ConditionalJobRuleParams conditionalJobRuleParams;

    private ConditionalJobRuleBuilder() {
        conditionalJobRuleParams = new ConditionalJobRuleParams();
    }

    public static ConditionalJobRuleBuilder create() {
        return new ConditionalJobRuleBuilder();
    }

    public ConditionalJobRuleBuilder addJob(ConditionalJobRuleJobParams job) {
        conditionalJobRuleParams.addJob(job);
        return this;
    }
    @SafeVarargs
    public final ConditionalJobRuleBuilder addCondition(String field, Pair<ConditionOperatorTypes, Object>... condition) {
        conditionalJobRuleParams.addCondition(field, condition);
        return this;
    }

    public ConditionalJobRuleParams build() {
        return conditionalJobRuleParams;
    }
}
