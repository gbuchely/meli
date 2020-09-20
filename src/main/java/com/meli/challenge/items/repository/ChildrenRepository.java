package com.meli.challenge.items.repository;

import com.meli.challenge.items.model.Children;
import com.meli.challenge.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, String> {
    List<Children> findByItemId(Item item);
}
