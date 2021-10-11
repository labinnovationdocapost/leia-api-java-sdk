package io.leia.builder.params;

import io.leia.builder.model.ConditionOperatorTypes;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConditionalJobRuleParams {
    private HashMap<String , List<HashMap<ConditionOperatorTypes, Object>>> conditions = new HashMap<>();
    private ArrayList<ConditionalJobRuleJobParams> jobs = new ArrayList<>();


    public HashMap<String, List<HashMap<ConditionOperatorTypes, Object>>> getConditions() {
        return conditions;
    }

    @SafeVarargs
    public final void addCondition(String key, Pair<ConditionOperatorTypes, Object>... values) {
        List<HashMap<ConditionOperatorTypes, Object>> list = new ArrayList<>();
        for(Pair<ConditionOperatorTypes, Object> value : values) {
            HashMap<ConditionOperatorTypes, Object> operator = new HashMap<>();
            operator.put(value.getLeft(), value.getRight());
            list.add(operator);
        }
        this.conditions.put(key, list);
    }

    public void setConditions(HashMap<String, List<HashMap<ConditionOperatorTypes, Object>>> conditions) {
        this.conditions = conditions;
    }

    public ArrayList<ConditionalJobRuleJobParams> getJobs() {
        return jobs;
    }

    public void addJob(ConditionalJobRuleJobParams job) {
        this.jobs.add(job);
    }
    public void setJobs(ArrayList<ConditionalJobRuleJobParams> jobs) {
        this.jobs = jobs;
    }
}
