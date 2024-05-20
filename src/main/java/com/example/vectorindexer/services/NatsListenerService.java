package com.example.vectorindexer.services;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NatsListenerService {
    
    @Autowired
    private VectorStore vectorStore;

    @PostConstruct
    public void init() throws Exception{
        log.info("Initializing NatsListenerService...");
    }

    
}
