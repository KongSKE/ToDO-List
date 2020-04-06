package com.example.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/")
public class ToDOserviceController {
    private static Map<String, List<ToDO>> data = new HashMap<>();
    private static List<ToDO> todoList = new ArrayList<>();

    static {
        ToDO knife = new ToDO("1", "knife");
        ToDO spoon = new ToDO("2", "spoon");
        ToDO fork = new ToDO("3", "fork");
        todoList.add(knife);
        todoList.add(spoon);
        todoList.add(fork);
        data.put("data", todoList);
    }

    @GetMapping
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ToDO todo) {
        todoList.add(todo);
        data.put("data", todoList);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody ToDO todo) {
        for (ToDO t : todoList) {
            if (t.getId().equals(id)) {
                todoList.remove(t);
                todoList.add(todo);
                data.put("data", todoList);
                return new ResponseEntity<>(data, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@RequestBody ToDO todo) {
        System.out.println(todoList.remove(todo));
        data.put("data", todoList);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @RequestMapping(value = "find/{id}")
    public ResponseEntity<Object> findProduct(@PathVariable("id") String id) {
        for (ToDO t : todoList) {
            if (t.getId().equals(id)) {
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("This todo doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
