package io.leia.client;

import com.google.common.io.ByteStreams;
import io.leia.Leia;
import io.leia.LeiaException;
import io.leia.builder.*;
import io.leia.builder.model.ConditionOperatorTypes;
import io.leia.client.api.ApplicationAdminApi;
import io.leia.client.model.*;
import io.leia.custom.client.tools.JobTools;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.lang3.tuple.Pair;

public class LeiaTest {

    public String serverUrl = "https://api.leia.io/leia/1.0.0";
    public String apiKey = "xxxxxxxxxxxxxxxxxxxx";
    public String applicationId = "5dceca1246eac2df484031de";

    public Model getOrCreateModel(Leia api) throws LeiaException, IOException {
        List<Model> models = api.getModels(GetModelsParamsBuilder.create().build());
        HashMap<String, Model> dico = new HashMap<>();
        for(Model model : models){
            System.out.println(model.getName() + " : " + model.getId());
            dico.put(model.getName(), model);
        }

        Model model;
        if(dico.containsKey("Classification demo.leia.io")){
            model = dico.get("Classification demo.leia.io");
        }
        else if(dico.containsKey("[TEST] Classification demo.leia.io")){
            model = dico.get("[TEST] Classification demo.leia.io");
        }
        else {
            model = api.addModel(AddModelParamsBuilder
                    .create(applicationId,
                            "[TEST] Classification demo.leia.io",
                            ByteStreams.toByteArray(this.getClass().getClassLoader().getResourceAsStream("mobilenet.model")))
                    .build());
        }
        return model;
    }

    @Test
    public void TestFetOrCreateModel() throws LeiaException, IOException {
        Leia api = new Leia(serverUrl, apiKey);
        Model model = getOrCreateModel(api);
        assert model != null;
    }

    @Test
    public void TestLink() throws IOException, LeiaException, ExecutionException, InterruptedException {
        ExecutorService s = Executors.newFixedThreadPool(10);
        Leia api = new Leia(serverUrl, apiKey);
        System.out.println("Connected to Leia");
        Model model = getOrCreateModel(api);
        System.out.println("Document found");
        Document doc = api.createDocument("test", ByteStreams.toByteArray(this.getClass().getClassLoader().getResourceAsStream("lorem_ipsum.pdf")));
        System.out.printf("Document created : %s%n", doc);
        Job job_image = api.transformDocuments(TransformDocumentParamsBuilder
                .create(doc, TransformTypes.IMAGE)
                .withOutputTag("PDF_IMAGES")
                .build());
        System.out.println("Job pdf to image created");
        Job job_predict = api.inferredDocuments(InferedDocumentParamsBuilder
                .create(model, doc)
                .withFormatType(FormatTypes.CLASSIFICATION)
                .executeAfter(job_image)
                .withPageRange("0")
                .build());

        System.out.println("Job classification created");

        ConditionalJobBuilder cjb = ConditionalJobBuilder.create(job_predict).addRule("r1",
                ConditionalJobRuleBuilder.create()
                        .addCondition("category", Pair.of(ConditionOperatorTypes.EQUAL, "AUTRE"))
                        .addCondition("accuracy", Pair.of(ConditionOperatorTypes.GREATER_OR_EQUAL, 0.5))
                        .addJob(ConditionalJobRuleJobBuilder.Transform.createImageToText().addDocument(doc).withTag("PDF_IMAGES").withPageRange("0").build())
                        .addJob(ConditionalJobRuleJobBuilder.Model.create(model).addDocument(doc).withFormatType(FormatTypes.CLASSIFICATION).withTag("PDF_IMAGES").withPageRange("0").build())
                        .build()
        );
        Job job_conditional_doc = api.conditionalJob(cjb.build());
        System.out.printf("Job conditional ocr + classif created : %s", job_conditional_doc.getId());
        System.out.println("Awaiting job");

        Future<Job> future_job_conditional = s.submit(() -> {
            return api.awaitJob(job_conditional_doc,1000);
        });
        Job job_conditional = future_job_conditional.get();
        System.out.println("Job finished");

        System.out.printf("Result type: %s", job_conditional.getResultType());
        System.out.printf("Result : \n%s", job_conditional.getResult());
        JobTools.Result result = JobTools.getResults(job_conditional);
        System.out.println(result.getConditional().get("r1"));
        System.out.println(result.getConditional().get("r1").getDocuments().get(0));
        System.out.println(result.getConditional().get("r1").getClassifications().get(0));
    }
    @Test
    public void All() throws IOException {
        try {
            Leia api = new Leia(serverUrl, apiKey);
            Document doc = api.createDocument("test", ByteStreams.toByteArray(this.getClass().getClassLoader().getResourceAsStream("lorem_ipsum.pdf")));
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
