package dev.gunlog.web;

import dev.gunlog.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/person")
public class PersonController {
    @GetMapping(path="{name}")
    public ResponseEntity<Person> gerPerson(@PathVariable String name) {
        return ResponseEntity.ok(new Person(name, 0));
    }
}