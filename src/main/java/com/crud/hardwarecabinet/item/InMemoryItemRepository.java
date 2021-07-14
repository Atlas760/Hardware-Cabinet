package com.crud.hardwarecabinet.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepository is a Spring interface declaring generic CRUD operations.
//In this case, it is used to store an Item along with a unique identifier of Long type
@Repository
public interface InMemoryItemRepository extends CrudRepository<Item, Long> {}