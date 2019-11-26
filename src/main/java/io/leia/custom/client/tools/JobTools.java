package io.leia.custom.client.tools;

import com.google.gson.Gson;
import io.leia.LeiaException;
import io.leia.client.JSON;
import io.leia.client.model.*;

import java.util.Map;

public class JobTools {
    static public <T> T getResult(Job job, Class<T> type) {
        ResultTypes result_type = job.getResultType();
        if(result_type == null)
            return (T)new JSON().deserialize(new JSON().serialize(job.getResult()), type);
        switch(result_type) {
            case DOCUMENT:
                return (T)new JSON().deserialize(new JSON().serialize(job.getResult()), Document.class);
            case LIST_DOCUMENT_:
                return (T)new JSON().deserialize(new JSON().serialize(job.getResult()), Document[].class);
            case DICT:
                return (T)new JSON().deserialize(new JSON().serialize(job.getResult()), Map.class);
            case CLASSIFICATION:
                return (T)new JSON().deserialize(new JSON().serialize(job.getResult()), Classification.class);

        }
        return null;
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
