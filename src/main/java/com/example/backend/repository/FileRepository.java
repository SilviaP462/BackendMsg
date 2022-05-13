package com.example.backend.repository;

import com.example.backend.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    @Transactional
    @Query("select u from File u where u.name=:name")
    Optional<File> findByName(String name);
}
