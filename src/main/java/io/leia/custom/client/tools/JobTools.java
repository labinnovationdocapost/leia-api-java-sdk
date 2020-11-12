package io.leia.custom.client.tools;

import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import io.leia.LeiaException;
import io.leia.client.JSON;
import io.leia.client.model.*;

import java.lang.reflect.Type;
import java.util.*;


public class JobTools {
    public static class Result {
        private List<Document> documents = new ArrayList<>();
        private List<Map<String, Classification>> classifications = new ArrayList<>();
        private List<Map<String, Map<String, Object>>> objects = new ArrayList<>();
        private Map<String, Result> conditionals = new HashMap<>();

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class JobTools.Result {\n");
            sb.append("    documents: ").append(documents.size()).append("\n");
            sb.append("    classifications: ").append(classifications.size()).append("\n");
            sb.append("    dictObjects: ").append(objects.size()).append("\n");
            sb.append("    conditionals: ").append(conditionals.size()).append("\n");
            sb.append("}");
            return sb.toString();
        }

        public List<Document> getDocuments() {
            return documents;
        }

        public List<Map<String, Classification>> getClassifications() {
            return classifications;
        }

        public List<Map<String, Map<String, Object>>> getObjects() {
            return objects;
        }

        public Map<String, Result> getConditional() {
            return conditionals;
        }
    }
    @Deprecated
    static public <T> T getResult(Job job) {
        ResultTypes result_type = job.getResultType();
        return getResult(result_type, job.getResult());
    }

    static private <T> T getResult(ResultTypes result_type, Object obj) {
        Type type;
        switch(result_type) {
            case DOCUMENT:
                return (T) Collections.singletonList(getResult(obj, Document.class));
            case LIST_DOCUMENT_:
                type = new TypeToken<List<Document>>() {}.getType();
                return (T)getResult(obj, type);
            case DICT_CLASSIFICATION_:
                type = new TypeToken<Map<String, Classification>>() {}.getType();
                return (T)getResult(obj, type);
            case CLASSIFICATION:
                HashMap<String, Classification> map = new HashMap<>();
                map.put(null, getResult(obj, Classification.class));
                return (T)map;
            case OBJECT:
                type = new TypeToken<Map<String, Object>>() {}.getType();
                return (T)getResult(obj, type);
            case DICT_OBJECT_:
                type = new TypeToken<Map<String, Map<String, Object>>>() {}.getType();
                return (T)getResult(obj, type);
        }
        return null;
    }
    static public Result getResults(Job job) {
        ResultTypes result_type = job.getResultType();
        return getResults(result_type, job.getResult());
    }
    static private Result getResults(ResultTypes result_type, Object obj) {
        Result result = new Result();
        return getResults(result_type, obj, result);
    }
    static private Result getResults(ResultTypes result_type, Object obj, Result result) {
        Type type;
        switch(result_type) {
            case DOCUMENT:
                result.documents.add(getResult(obj, Document.class));
                break;
            case LIST_DOCUMENT_:
                result.documents.addAll(getResult(obj, new TypeToken<List<Document>>() {}.getType()));
                break;
            case DICT_CLASSIFICATION_:
                result.classifications.add(getResult(obj, new TypeToken<Map<String, Classification>>() {}.getType()));
                break;
            case CLASSIFICATION:
                Map<String, Classification> m_classif = new HashMap<>();
                m_classif.put(null, getResult(obj, Classification.class));
                result.classifications.add(m_classif);
                break;
            case OBJECT:
                Map<String, Map<String, Object>> m_objects = new HashMap<>();
                m_objects.put(null, getResult(obj, new TypeToken<Map<String, Object>>() {}.getType()));
                result.objects.add(m_objects);
                break;
            case DICT_OBJECT_:
                result.objects.add(getResult(obj, new TypeToken<Map<String, Map<String, Object>>>() {}.getType()));
                break;
            case CONDITIONAL:
                for(Map.Entry<String, List<LinkedTreeMap>> sub_json_result_map : (Set<Map.Entry>)((LinkedTreeMap)obj).entrySet()) {
                    Result sub_result = new Result();
                    for(LinkedTreeMap sub_json_result : sub_json_result_map.getValue()) {
                        result_type = ResultTypes.fromValue((String)sub_json_result.get("result_type"));
                        getResults(result_type, sub_json_result.get("result"), sub_result);
                    }
                    result.getConditional().put(sub_json_result_map.getKey(), sub_result);
                }
                break;


        }
        return result;
    }

    static public <T> T getResult(Job job, Class<T> type) {
        return getResult(job.getResult(), (Type)type);
    }
    static public <T> T getResult(Object obj, Type type) {
        return (T) new JSON().deserialize(new JSON().serialize(obj), type);
    }


    static public Boolean isJobFinish(Job job) throws LeiaException {
        if(job.getStatus() == Statuses.PROCESSED) {
            return true;
        }
        if(job.getStatus() == Statuses.FAILED) {
            throw new LeiaException(String.format("Job Failed, %s", job.getReason()), null);
        }
        if(job.getStatus() == Statuses.CANCELED) {
            throw new LeiaException(String.format("Job canceled, %s", job.getReason()), null);
        }
        return false;
    }
}
