package hasebul.ai.hitman.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;


    public RagController(ChatClient.Builder client, VectorStore vectorStore) {
        this.chatClient = client.build();
        this.vectorStore = vectorStore;
    }

    @GetMapping("/chat")
    public ResponseEntity<ChatResponse> getChat(@RequestParam("message") String message){
        SearchRequest searchRequest = SearchRequest.builder()
                .query(message)
                .topK(7)
                .similarityThreshold(0.7)
                .build();

        List<Document> result = vectorStore.similaritySearch(searchRequest);
        String context = result.stream()
                .map(Document::getText)
                .reduce("", (a, b) -> a + "\n---\n" + b);

        String prompt = """
                You are an AI assistant. Answer ONLY from the context.

                Context:
                %s

                Question:
                %s

                If not found, say "I don't know".
                """.formatted(context, message);
        ChatResponse ans = chatClient.prompt()
                .user(prompt)
                .call()
                .chatResponse();
        return ResponseEntity.ok(ans);
    }
}
