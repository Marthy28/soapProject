package com.example.endpoint;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.repository.AuthorRepository;

import library.author.AddAuthorRequest;
import library.author.AddAuthorResponse;
import library.author.Author;
import library.author.DeleteAuthorRequest;
import library.author.DeleteAuthorResponse;
import library.author.GetAllAuthorsResponse;
import library.author.GetAuthorById;
import library.author.GetAuthorByIdResponse;
import library.author.UpdateAuthorRequest;
import library.author.UpdateAuthorResponse;
@Configuration
@Endpoint
public class AuthorEndpoint {
	private static final String NAMESPACE_URI = "library/Author";

	private AuthorRepository authorRepository;

	@Autowired
	public AuthorEndpoint(AuthorRepository _AuthorRepository) {
		this.authorRepository = _AuthorRepository;
	}

	//récupération d'un livre par l'id
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorById")
	@ResponsePayload
	public GetAuthorByIdResponse getAuthorById(@RequestPayload GetAuthorById request) {
		GetAuthorByIdResponse response = new GetAuthorByIdResponse();
		response.setAuthor(authorRepository.findAuthorById(request.getId()));

		return response;
	}
	
	//récupération de tous les livres
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllAuthorsRequest")
	public GetAllAuthorsResponse getAllAuthors() {
		GetAllAuthorsResponse response = new GetAllAuthorsResponse();
		List<Author> AuthorTypeList = new ArrayList<>();
		List<Author> AuthorList = authorRepository.getAllEntities();
		for (int i = 0; i < AuthorList.size(); i++) {
			Author Author = new Author();
			AuthorTypeList.add(Author);    
		}
		response.getAuthor().addAll(AuthorTypeList);
		return response;
}
	
	//Ajout d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAuthorRequest")
	public AddAuthorResponse addAuthor(@RequestPayload AddAuthorRequest request) {
		AddAuthorResponse response = new AddAuthorResponse();	
		Author author = new Author();
		author.setFirstName(request.getFirstName());
		author.setLastName(request.getLastName());
		author.setBirth(request.getBirth());
		
		response.setAuthor(author);
				
		return response;
}
	
//suppression d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteAuthorRequest")
	public DeleteAuthorResponse deleteAuthors(@RequestPayload DeleteAuthorRequest request) {

		DeleteAuthorResponse response = new DeleteAuthorResponse();
		
		authorRepository.deleteEntityById(request.getId());
		return response;
}
	
	//mise à jour d'un livre
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAuthorRequest")
	public UpdateAuthorResponse updateAuthors(@RequestPayload UpdateAuthorRequest request) {
		
		Author Author = new Author();
		authorRepository.updateEntity(Author);
		UpdateAuthorResponse response = new UpdateAuthorResponse();
		return response;
}
}
