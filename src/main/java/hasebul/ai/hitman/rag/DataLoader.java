package hasebul.ai.hitman.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {
    private final VectorStore vectorStore;

    public DataLoader(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    private void load(){
        List<Document> documents = List.of(
                new Document("Artificial intelligence is transforming industries through automation, data analysis, and predictive modeling."),
                new Document("Spring Boot simplifies Java backend development by reducing boilerplate configuration."),
                new Document("Docker containers ensure consistent environments across development and production systems."),
                new Document("Kubernetes orchestrates containerized applications at scale with resilience and automation."),
                new Document("Microservices architecture promotes modularity but introduces complexity in communication and deployment."),
                new Document("GraphQL provides flexible APIs compared to traditional REST endpoints."),
                new Document("Machine learning models require high-quality data for accurate predictions."),
                new Document("Deep learning excels in image recognition, natural language processing, and speech synthesis."),
                new Document("Cloud computing enables scalable infrastructure without heavy upfront investment."),
                new Document("Cybersecurity is critical as digital systems become increasingly interconnected."),

                new Document("PostgreSQL is a powerful relational database with strong support for advanced queries."),
                new Document("Indexing improves database performance but increases storage overhead."),
                new Document("Redis is commonly used for caching to reduce database load and latency."),
                new Document("Elasticsearch allows fast full-text search across large datasets."),
                new Document("Data consistency and availability often require trade-offs in distributed systems."),

                new Document("Angular provides a structured framework for building large-scale frontend applications."),
                new Document("React focuses on component-based UI development with a virtual DOM."),
                new Document("TypeScript adds type safety to JavaScript, improving maintainability."),
                new Document("Frontend performance depends heavily on efficient rendering and network optimization."),
                new Document("Progressive Web Apps combine web and mobile app capabilities."),

                new Document("Clean code emphasizes readability, simplicity, and maintainability."),
                new Document("Design patterns like Singleton and Factory help solve common software problems."),
                new Document("Refactoring improves code quality without changing functionality."),
                new Document("Unit testing ensures that individual components behave as expected."),
                new Document("Continuous integration helps detect issues early in development."),

                new Document("DevOps practices bridge the gap between development and operations teams."),
                new Document("CI/CD pipelines automate testing and deployment processes."),
                new Document("Monitoring systems help detect anomalies and maintain uptime."),
                new Document("Logging provides insights into system behavior during failures."),
                new Document("Infrastructure as code allows version-controlled environment configuration."),

                new Document("Healthy eating habits contribute to long-term physical and mental well-being."),
                new Document("Regular exercise improves cardiovascular health and energy levels."),
                new Document("Sleep is essential for cognitive function and recovery."),
                new Document("Stress management techniques include meditation and time management."),
                new Document("Hydration plays a crucial role in maintaining body functions."),

                new Document("Philosophy explores questions about existence, knowledge, and ethics."),
                new Document("Stoicism teaches resilience and control over emotions."),
                new Document("Existentialism emphasizes individual freedom and responsibility."),
                new Document("Critical thinking is essential for evaluating information objectively."),
                new Document("Ethics guides decision-making in personal and professional life."),

                new Document("Mars missions aim to explore the possibility of human colonization."),
                new Document("Black holes challenge our understanding of physics and spacetime."),
                new Document("Astronomy relies heavily on advanced telescopes and data analysis."),
                new Document("The universe is estimated to be billions of years old."),

                new Document("Books are a powerful source of knowledge and imagination."),
                new Document("Music influences emotions and cultural identity."),
                new Document("Travel broadens perspectives and exposes people to new cultures."),
                new Document("Technology is reshaping how humans interact and communicate."),
                new Document("Education is a lifelong process that drives personal growth.")
        );

        vectorStore.add(documents);
    }
}
