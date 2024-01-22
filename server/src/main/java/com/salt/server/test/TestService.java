package com.salt.server.test;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test getTestByName(String name) {
        return testRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("test not found"));
    }
}
