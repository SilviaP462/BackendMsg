package com.example.backend.service;

import com.example.backend.model.Item;
import com.example.backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.List;

@Component
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        List<Item> items = (List<Item>) itemRepository.findAll();
        return items;
    }

    @Transactional
    public Item addItem(Item item) {
        if (item!=null)
            itemRepository.save(item);
        return item;
    }

    @Transactional
    public Item removeItem(Long id) {
        if (findItemById(id) == null)
            return null;
       itemRepository.deleteById(id);
       System.out.println(id);
       return findItemById(id);
    }

    public Item findItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null)
            return null;
        return item;
    }

    @Transactional
    public Item updateById(Item itemWithUpdates) throws ValidationException {
        //String loggedInUser= SecurityContextHolder.getContext().getAuthentication().getName();

        Item item=findItemById(itemWithUpdates.getIdItem());
        Item oldItem=new Item();

        oldItem.setName(item.getName());
        oldItem.setDescription(item.getDescription());
        oldItem.setStatus(item.getStatus());

        if (!itemWithUpdates.getName().equals("")
                && !itemWithUpdates.getDescription().equals("")) {

            item.setName(itemWithUpdates.getName());
            item.setDescription(itemWithUpdates.getDescription());
            item.setStatus(itemWithUpdates.getStatus());
        } else {
            throw new ValidationException("One of the fields was not valid");
        }

        itemRepository.save(item);
        return item;
    }

}
