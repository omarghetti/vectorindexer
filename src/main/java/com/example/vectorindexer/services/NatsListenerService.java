package com.example.vectorindexer.services;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.qdrant.QdrantVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Nats;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NatsListenerService {
    
    @Autowired
    private QdrantVectorStore vectorStore;

    @Value("${nats.server.url}")
    private String natsServerUrl;

    @Value("${nats.server.subject}")
    private String natsServerSubject;

    @PostConstruct
    public void init() throws Exception{
        log.info("Initializing NatsListenerService...");
        log.info("Nats server url: {}", natsServerUrl);
        log.info("Nats server subject: {}", natsServerSubject);
        Connection nc = Nats.connect(natsServerUrl);
        Dispatcher natsDispatcher = nc.createDispatcher((msg) -> {
            String message = new String(msg.getData());
            log.info("Received message: {}", message);
            Document document = new Document(message);
            vectorStore.add(List.of(document));
        });
        natsDispatcher.subscribe(natsServerSubject);
    }
    
}
