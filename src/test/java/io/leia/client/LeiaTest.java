package io.leia.client;

import com.google.gson.reflect.TypeToken;
import io.leia.Leia;
import io.leia.LeiaException;
import io.leia.builder.*;
import io.leia.client.model.*;
import io.leia.custom.client.tools.JobTools;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class LeiaTest {
    public Model getOrCreateModel(Leia api) throws LeiaException, IOException {
        List<Model> models = api.getModels(GetModelsParamsBuilder.create().build());
        HashMap<String, Model> dico = new HashMap<>();
        for(Model model : models){
            System.out.println(model.getName() + " : " + model.getId());
            dico.put(model.getName(), model);
        }

        Model model = null;
        if(dico.containsKey("transverse2")){
            model = dico.get("transverse2");
        }
        else {
            model = api.addModel(AddModelParamsBuilder
                    .create("5dceca1246eac2df484031de",
                            "transverse2",
                            Files.readAllBytes(Paths.get("C:\\Users\\ctisserand\\Downloads\\5d1dc84b76134ea8778f5d35")))
                    .build());
        }
        return model;
    }

    @Test
    public void TestFetOrCreateModel() throws LeiaException, IOException {
        Leia api = new Leia("http://127.0.0.1:8080/leia/1.0.0", "p4vxl6NhF08rHHZOBXWIhhYIDlBLfz");
//        Leia api = new Leia("https://api.leia.io/leia/1.0.0", "c7wyJqqNgu8g4Vnapob4Ekp0rTobSO");
        Model model = getOrCreateModel(api);
        assert model != null;
    }

    @Test
    public void TestLink() throws IOException, LeiaException, ExecutionException, InterruptedException {
        Leia api = new Leia("http://127.0.0.1:8080/leia/1.0.0", "p4vxl6NhF08rHHZOBXWIhhYIDlBLfz");
        Model model = getOrCreateModel(api);
        Document doc = api.createDocument("test", Files.readAllBytes(Paths.get("C:\\Users\\ctisserand\\Documents\\Scanned-image_18-02-2019-150749.pdf")));
        Job job_image = api.transformDocuments(TransformDocumentParamsBuilder
                .create(doc, TransformTypes.IMAGE)
                .build());
        Job job_text_doc = api.transformDocuments(TransformDocumentParamsBuilder
                .create(doc, TransformTypes.TEXT)
                .executeAfter(job_image)
                .build());
        Job job_predict_doc = api.inferredDocuments(InferedDocumentParamsBuilder
                .create(model, doc)
                .withFormatType(FormatTypes.CLASSIFICATION)
                .executeAfter(job_image)
                .withPageRange("0")
                .build());

        ExecutorService s = Executors.newFixedThreadPool(10);
        Future<Job> future_job_text = s.submit(() -> {
            return api.awaitJob(job_text_doc,1000);
        });
        Future<Job> future_job_predict = s.submit( () -> {
            return api.awaitJob(job_predict_doc,1000);
        });



        Job job_text = future_job_text.get();
        Job job_predict = future_job_predict.get();

//        Document[] j = JobTools.getResult(job_text, Document[].class);
//        System.out.println(Arrays.stream(j).map(Document::getId).collect(Collectors.joining(",")));
//        System.out.println(Arrays.stream(j).map(x -> {
//            try {
//                return (String)api.getDocumentContent(GetDocumentContentParamsBuilder.create(x).returnAs(String.class).build());
//            } catch (LeiaException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }).collect(Collectors.joining("\n********************************************************************************\n")));
        System.out.println(job_predict.getResultType());
        Map<String,Classification> m = JobTools.getResult(job_predict);
        System.out.println(m.keySet());
    }
    @Test
    public void All() throws IOException {
        try {
            Leia api = new Leia("http://127.0.0.1:8080/leia/1.0.0", "p4vxl6NhF08rHHZOBXWIhhYIDlBLfz");
//            api.login();
            Document doc = api.createDocument("test", Files.readAllBytes(Paths.get("C:\\Users\\ctisserand\\Documents\\Scanned-image_18-02-2019-150749.pdf")));
            Job job = api.transformDocuments(TransformDocumentParamsBuilder.create(doc, TransformTypes.IMAGE).build());
            job = api.awaitJob(job, 1000);
            Document[] j = JobTools.getResult(job, Document[].class);
            System.out.println(j[0].getId());

            job = api.transformDocuments(TransformDocumentParamsBuilder.create(j[0], TransformTypes.TEXT).build());
            JobTools.isJobFinish(job);
            job = api.awaitJob(job,1000);
            Document m2 = JobTools.getResult(job, Document.class);
            System.out.println(m2);

            Document doc_text = api.getDocument(m2.getId());
            System.out.println((String)api.getDocumentContent(GetDocumentContentParamsBuilder.create(doc_text).returnAs(String.class).build()));
//
//            doc = api.getDocument(doc);
//            api.getDocumentContent(doc, byte[].class);
//
            Model model = getOrCreateModel(api);
            System.out.println(model.getModelType());

            job = api.inferredDocuments(InferedDocumentParamsBuilder
                    .create(model.getId(), j[0])
                    .withFormatType(FormatTypes.CLASSIFICATION)
                    .build());
            job = api.awaitJob(job,1000);
            System.out.println(JobTools.getResult(job, Classification.class));
        } catch (LeiaException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
