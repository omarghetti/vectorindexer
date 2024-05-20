package com.example.vectorindexer.config;

import org.springframework.ai.transformers.TransformersEmbeddingClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "embeddingclient.onnx")
public class EmbeddingClientConfig {
    
    @Getter
    @Setter
    private String tokenizerResource = "onnx-transformers/tokenizer.json";

    @Getter
    @Setter
    private String modelResource = "onnx-transformers/model.onnx";

    @Bean
    public TransformersEmbeddingClient onnxEmbeddingClient() throws Exception {
        TransformersEmbeddingClient client = new TransformersEmbeddingClient();
        client.setTokenizerResource(this.getTokenizerResource());
        client.setModelResource(this.getModelResource());
        client.setModelOutputName("token_embeddings");
        client.setDisableCaching(true);
        client.afterPropertiesSet();
        return client;
    }
    
}
