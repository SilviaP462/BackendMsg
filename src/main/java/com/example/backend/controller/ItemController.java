package com.example.backend.controller;

import com.example.backend.model.Item;
import com.example.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", originPatterns = "*", allowedHeaders = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/items/add")
    public Item addItem(@RequestBody Item item) {
        return itemService.addItem(item);
    }

    @DeleteMapping("/items/delete/{{idItem}}")
    @ResponseStatus(HttpStatus.OK)
    public Item deleteItemById1(@PathVariable("idItem") Long id) {
        return itemService.removeItem(id);
    }

    @DeleteMapping("/items/ok")
    //@ResponseStatus(HttpStatus.OK)
    public Item deleteItemById(@RequestParam("idItem") Long id) {
        return itemService.removeItem(id);
    }

    @PostMapping("/items/update/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Item> updateById(@RequestBody Item item) throws ValidationException {
        return new ResponseEntity<>(itemService.updateById(item), HttpStatus.OK);
    }
}
