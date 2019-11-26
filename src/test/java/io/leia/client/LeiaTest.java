package io.leia.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.leia.client.model.*;
import org.junit.Test;

import io.leia.Leia;
import io.leia.LeiaException;
import io.leia.LeiaTransformationEnum;
import io.leia.custom.client.tools.JobTools;

public class LeiaTest {
    @Test
    public void All() {
        try {
            Leia api = new Leia("http://127.0.0.1:8080/leia/1.0.0", "p4vxl6NhF08rHHZOBXWIhhYIDlBLfz");
//            api.login();
            Document doc = api.createDocument("test", new FileInputStream("C:\\Users\\ctisserand\\Documents\\Scanned-image_18-02-2019-1507493.pdf"));
            Job job = api.transformDocuments(Collections.singletonList(doc), TransformTypes.IMAGE);
            job = api.awaitJob(job);
            Document[] j = JobTools.getResult(job, Document[].class);
            System.out.println(j[0].getId());

            job = api.transformDocuments(Collections.singletonList(j[0]), TransformTypes.TEXT);
            JobTools.isJobFinish(job);
            job = api.awaitJob(job);
            Document m2 = JobTools.getResult(job, Document.class);
            System.out.println(m2);

            Document doc_text = api.getDocument(m2.getId());
            System.out.println(api.getDocumentContent(doc_text, String.class));
//
//            doc = api.getDocument(doc);
//            api.getDocumentContent(doc, byte[].class);
//
            List<Model> models = api.getModels();
            HashMap<String, Model> dico = new HashMap<>();
            for(Model model : models){
                System.out.println(model.getName() + " : " + model.getId());
                dico.put(model.getName(), model);
            }

            Model model = null;
            if(dico.containsKey("transverse")){
                model = dico.get("transverse");
            }
            else {
                model = api.addModel("5dceca1246eac2df484031de", "transverse", new FileInputStream("C:\\Users\\ctisserand\\Downloads\\5d1dc84b76134ea8778f5d35"), null, null,null,null,null);
            }
            System.out.println(model.getModelType());

            job = api.inferredDocuments(model.getId(), Collections.singletonList(j[0]), null, FormatTypes.CLASSIFICATION);
            job = api.awaitJob(job);
            System.out.println(JobTools.getResult(job, Map.class));
        } catch (LeiaException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
