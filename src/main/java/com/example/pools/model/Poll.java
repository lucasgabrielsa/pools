package com.example.pools.model;

import com.example.pools.model.audit.UserDateAudit;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String question;

    @Size(min = 2, max = 6)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 30)
    private List<Choice> choices = new ArrayList<Choice>();

    @NotNull
    private Instant expirationDatetime;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public Instant getExpirationDatetime() {
        return expirationDatetime;
    }

    public void setExpirationDatetime(Instant expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addChoice(Choice choice) {
        this.choices.add(choice);
    }

    public void removeChoice(Choice choice) {
        this.choices.remove(choice);
        choice.setPoll(null);
    }


}
