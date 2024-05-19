package com.example.vectorindexer.config;

import org.springframework.ai.transformers.TransformersEmbeddingClient;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore.QdrantVectorStoreConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "vectorstore.qdrant")
public class VectorStoreConfig {

    @Getter
    @Setter
    private String host;

    @Getter
    @Setter
    private int port;

    @Getter
    @Setter
    private String collectionName;

    @Bean
    public QdrantVectorStoreConfig qdrantVectorStoreConfig() {
        return QdrantVectorStoreConfig.builder()
                .withHost(this.getHost())
                .withPort(this.getPort())
                .withCollectionName(this.getCollectionName())
                .build();
        
    }

    @Bean
    public VectorStore vectorStore(final QdrantVectorStoreConfig qdrantVectorStoreConfig, final TransformersEmbeddingClient embeddingClient) {
        return new QdrantVectorStore(qdrantVectorStoreConfig, embeddingClient);
    }
    
}
