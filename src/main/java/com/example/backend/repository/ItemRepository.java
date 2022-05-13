package com.example.backend.repository;

import com.example.backend.model.File;
import com.example.backend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Transactional
    @Query("select u from Item u where u.idItem=:id")
    Optional<Item> findItemById(Long id);

    @Transactional
    @Query("select u from Item u where u.name=:name")
    Optional<Item> findByName(String name);


}
