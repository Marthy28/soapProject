package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import library.author.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IAuthorRepository extends CrudRepository<Author, Integer> {}