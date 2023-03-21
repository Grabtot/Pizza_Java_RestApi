package edu.itstep.restapi.controller;

import edu.itstep.restapi.entity.Pizza;
import edu.itstep.restapi.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class Controller {
    @Autowired
    private PizzaService service;

    @GetMapping("")
    public List<Pizza> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Pizza getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("")
    public Pizza addPizza(@RequestBody Pizza pizza){
        return service.create(pizza);
    }

    @PutMapping("")
    public Pizza updatePizza(@RequestBody Pizza pizza){
        return service.update(pizza);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        return service.deleteById(id);
    }
}
