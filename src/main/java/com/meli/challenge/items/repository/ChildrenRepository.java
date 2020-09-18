package com.meli.challenge.items.repository;

import com.meli.challenge.items.model.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, String> {
}
