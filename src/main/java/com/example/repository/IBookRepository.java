package com.example.repository;

import org.springframework.data.repository.CrudRepository;


import library.book.Book;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IBookRepository extends CrudRepository<Book, Integer> {}