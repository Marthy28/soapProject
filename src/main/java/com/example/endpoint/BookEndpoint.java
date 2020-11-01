package com.example.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.repository.BookRepository;

import library.book.*;
@Configuration
@Endpoint
public class BookEndpoint {
	private static final String NAMESPACE_URI = "library/book";

	private BookRepository bookRepository;

	@Autowired
	public BookEndpoint(BookRepository _bookRepository) {
		this.bookRepository = _bookRepository;
	}

	//récupération d'un livre par l'id
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookById")
	@ResponsePayload
	public GetBookByIdResponse getBookById(@RequestPayload GetBookById request) {
		GetBookByIdResponse response = new GetBookByIdResponse();
		response.setBook(bookRepository.findBookById(request.getId()));

		return response;
	}
	
	//récupération de tous les livres
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBooksRequest")
	public GetAllBooksResponse getAllBooks() {
		GetAllBooksResponse response = new GetAllBooksResponse();
		List<Book> BookTypeList = new ArrayList<>();
		List<Book> BookList = bookRepository.getAllEntities();
		for (int i = 0; i < BookList.size(); i++) {
			Book book = new Book();
			BookTypeList.add(book);    
		}
		response.getBookType().addAll(BookTypeList);
		return response;
}
	
	//Ajout d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
	public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
		AddBookResponse response = new AddBookResponse();	
		Book book = new Book();
		book.setTitle(request.getTitle());
		book.setIsbn(request.getISBN());
		book.setDate(request.getDate());
		
		Book BookInfo = new Book();
		BeanUtils.copyProperties(book, BookInfo);
		response.setBook(BookInfo);
				
		return response;
}
	
//suppression d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBookRequest")
	public DeleteBookResponse deleteBooks(@RequestPayload DeleteBookRequest request) {

		DeleteBookResponse response = new DeleteBookResponse();
		
		bookRepository.deleteEntityById(request.getId());
		return response;
}
	
	//mise à jour d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBookRequest")
	public UpdateBookResponse updateBooks(@RequestPayload UpdateBookRequest request) {
		
		Book Book = new Book();
		bookRepository.updateEntity(Book);
		UpdateBookResponse response = new UpdateBookResponse();
		return response;
}
}
