package com.meli.challenge.items.repository;

import com.meli.challenge.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, String> {
}
