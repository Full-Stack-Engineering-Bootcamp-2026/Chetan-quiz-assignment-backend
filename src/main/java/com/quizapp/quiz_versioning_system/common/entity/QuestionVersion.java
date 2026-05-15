package com.quizapp.quiz_versioning_system.common.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;

import java.util.UUID;

@Entity
public class QuestionVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer versionNumber;

    private Boolean latest = true;

    @Lob
    private String questionText;

    @Enumerated(EnumType.STRING)
    private AnswerType answerType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_master_id")
    private QuestionMaster questionMaster;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "questionVersion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionOption> options;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @PrePersist
    public void generateUuid() {

        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}