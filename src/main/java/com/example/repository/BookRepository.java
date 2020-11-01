package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import library.book.*;

@Component
public class BookRepository{
	//private static final Map<String, Book> books = new HashMap<>();

	@Autowired
	private IBookRepository repo;
	
	public Book findBookById(int id) {
		Assert.notNull(id, "The Book's name must not be null");
		return repo.findById(id).get();
	}

	public List<Book> getAllEntities() {
        List <Book> list = new ArrayList < > ();
        repo.findAll().forEach(e -> list.add(e));
        return list;
	}

	public void deleteEntityById(int id) {
		repo.deleteById(id);
		
	}

	public void updateEntity(Book book) {
		repo.save(book);
	}

}
