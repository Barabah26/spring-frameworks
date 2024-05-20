package com.example.bootdata.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jobs")
@Getter
@Setter
public class Job {
    @Id
    @Column(name = "job_id", nullable = false, length = 10)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "job_title", nullable = false, length = 35)
    private String jobTitle;

    public Job() {
    }

    public Job(String id, String jobTitle) {
        this.id = id;
        this.jobTitle = jobTitle;
    }

    @Override public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}