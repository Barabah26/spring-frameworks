package com.example.boothibernate.resource;

import com.example.boothibernate.domain.Student;
import com.example.boothibernate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class RestStudentController {

    private final StudentService studentService;
    @GetMapping("/group/{id}")
    public ResponseEntity<?> getByGroupId(@PathVariable("id") Long groupId) {
        Optional<List<Student>> studentList = studentService.getAllByGroupId(groupId);
        if (studentList.isEmpty()){
            return ResponseEntity.badRequest().body("Student not found");
        }
        return ResponseEntity.ok().body(studentList.get());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long studentId){
        Student student = studentService.get(studentId);
        if (student == null){
            return ResponseEntity.badRequest().body("Student not found");
        }
        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    public void create(@RequestBody Student student){
        studentService.create(student);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student){
        try {
            studentService.update(student);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long studentId){
        try {
            studentService.delete(studentId);
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
