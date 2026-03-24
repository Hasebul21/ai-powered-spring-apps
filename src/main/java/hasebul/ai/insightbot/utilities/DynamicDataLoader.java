package hasebul.ai.insightbot.utilities;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DynamicDataLoader {

    private final VectorStore vectorStore;

    @Value("classpath:cefalo_hr_policy.pdf")
    Resource hr_policy;

    public DynamicDataLoader(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    private void load(){
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(hr_policy);
        List<Document> documentList = tikaDocumentReader.read();
        //vectorStore.add(documentList);
        TextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(100)
                .withMaxNumChunks(400)
                .build();
        vectorStore.add(splitter.apply(documentList));

    }
}
