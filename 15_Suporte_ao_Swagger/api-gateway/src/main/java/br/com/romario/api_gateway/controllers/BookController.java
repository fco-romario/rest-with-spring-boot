package br.com.romario.api_gateway.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.romario.api_gateway.data.vo.v1.BookVO;
import br.com.romario.api_gateway.services.BookService;
import br.com.romario.api_gateway.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {
	
	@Autowired
	private BookService bookService;
    
    @GetMapping(produces =  { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    @Operation(summary = "Finds all Book", description = "Finds all Book", tags = { "Book"},
    	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
				content = { 
					@Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = BookVO.class))),
					@Content(mediaType = MediaType.APPLICATION_XML, array = @ArraySchema(schema = @Schema(implementation = BookVO.class))),
					@Content(mediaType = MediaType.APPLICATION_YML, array = @ArraySchema(schema = @Schema(implementation = BookVO.class)))
			}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    	})
    public List<BookVO> findAll() {
    	return bookService.findAll();
    }
    
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    @Operation(summary = "Finds a Book", description = "Finds a Book", tags = { "Book"},
		responses = {
			@ApiResponse(description = "Success", responseCode = "200", content = { @Content(schema = @Schema(implementation = BookVO.class)) }),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
    public BookVO findById(@PathVariable(value = "id") Long id) {
    	return bookService.findById(id);
    }
    
    @PostMapping(
    		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    @Operation(
    	summary = "Adds a new Book",
    	description = "Adds a new Book by passing in a JSON, XML or YML representation of the book",
    	tags = { "Book"},
		responses = {
			@ApiResponse(description = "Created", responseCode = "201", content = { @Content(schema = @Schema(implementation = BookVO.class)) }),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),		
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    	})
    public ResponseEntity<BookVO> create(@RequestBody BookVO book) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }
    
    @PutMapping(
    		produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
    		consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }) 
    @Operation(
    	summary = "Update a new Book",
    	description = "Update a Book by passing in a JSON, XML or YML representation of the book",
    	tags = { "Book"},
		responses = {
			@ApiResponse(description = "Updated", responseCode = "200", content = { @Content(schema = @Schema(implementation = BookVO.class)) }),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),		
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    	})
    public BookVO Update(@RequestBody BookVO book) {
    	return bookService.update(book);
    }
    
    @DeleteMapping(value = "{id}")
    @Operation(
    	summary = "Update a new Book",
    	description = "Update a Book by passing in a JSON, XML or YML representation of the book",
    	tags = { "Book"},
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),			
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),		
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    	})
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    	bookService.delete(id);
    	return ResponseEntity.noContent().build();
    }
}
