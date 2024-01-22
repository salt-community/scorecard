package com.salt.server.github;


import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @UuidGenerator
    private UUID id;
    private String url;
    private int commit;
    private int issue;
    private int duration;
    private int performance;
    private int testCoverage;
    @ManyToOne
    @JoinColumn(name = "project_id", insertable = false, updatable = false)
    private Github githubId;



}
