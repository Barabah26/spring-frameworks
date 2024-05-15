package com.example.boothibernate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
//@ToString(of = {"id", "name"})
@Entity
@Table(name="groups")
//@NamedEntityGraph(name = "groupWithStudents",
//        attributeNodes = @NamedAttributeNode("students"))
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Student> students;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", students=" + students +
                '}';
    }
}