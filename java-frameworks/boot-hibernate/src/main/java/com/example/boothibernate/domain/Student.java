package com.example.boothibernate.domain;

import lombok.*;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @Column(name = "group_id")
    private Long groupId;
//    @ManyToOne
//    private Group group;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
//                ", group=" + group.getName() +
                ", group=" + groupId +
                '}';
    }
}
