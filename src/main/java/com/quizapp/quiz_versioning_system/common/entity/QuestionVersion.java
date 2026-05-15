package com.quizapp.quiz_versioning_system.common.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.quizapp.quiz_versioning_system.common.enums.AnswerType;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class QuestionVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer versionNumber;

    private Boolean latest = true;

    @Column(columnDefinition = "TEXT")
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