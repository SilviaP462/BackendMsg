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

    @DeleteMapping("/items/delete/")
    public Boolean deleteItemById(@RequestBody Item item) {
        return itemService.removeItem(item.getIdItem());
    }

    @PostMapping("/items/update/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Item> updateById(@RequestBody Item item) throws ValidationException {
        return new ResponseEntity<>(itemService.updateById(item), HttpStatus.OK);
    }
}
