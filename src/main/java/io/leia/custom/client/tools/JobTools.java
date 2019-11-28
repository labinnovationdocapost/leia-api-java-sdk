package io.leia.custom.client.tools;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.leia.LeiaException;
import io.leia.client.JSON;
import io.leia.client.model.*;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobTools {
    static public <T> T getResult(Job job) {
        Type type;
        ResultTypes result_type = job.getResultType();
        switch(result_type) {
            case DOCUMENT:
                return (T) Collections.singletonList(getResult(job, Document.class));
            case LIST_DOCUMENT_:
                type = new TypeToken<List<Document>>() {}.getType();
                return (T)getResult(job, type);
            case DICT_CLASSIFICATION_:
                type = new TypeToken<Map<String, Classification>>() {}.getType();
                return (T)getResult(job, type);
            case CLASSIFICATION:
                HashMap<String, Classification> map = new HashMap<>();
                map.put(job.getDocumentIds().get(0), getResult(job, Classification.class));
                return (T)map;
            case OBJECT:
                type = new TypeToken<Map<String, Object>>() {}.getType();
                return (T)getResult(job, type);
            case DICT_OBJECT_:
                type = new TypeToken<Map<String, Map<String, Object>>>() {}.getType();
                return (T)getResult(job, type);
        }
        return null;
    }

    static public <T> T getResult(Job job, Class<T> type) {
        return getResult(job, (Type)type);
    }
    static public <T> T getResult(Job job, Type type) {
        return (T) new JSON().deserialize(new JSON().serialize(job.getResult()), type);
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
