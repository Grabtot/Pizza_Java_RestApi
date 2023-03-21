package edu.itstep.restapi.service;

import edu.itstep.restapi.entity.Pizza;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PizzaService {

    List<Pizza> getAll();

    Pizza getById(int id);

    Pizza create(Pizza pizza);

    Pizza update(Pizza pizza);

    ResponseEntity<?> deleteById(int id);
}
