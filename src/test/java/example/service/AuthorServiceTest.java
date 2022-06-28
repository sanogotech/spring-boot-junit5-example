package example.service;

import example.dto.AuthorDto;
import example.exception.EntityNotFoundException;
import example.model.Author;
import example.repository.AuthorRepository;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Author Service Unit Tests")
public class AuthorServiceTest {

    private AuthorRepository authorRepositoryMock;
    private AuthorService authorService;


    @BeforeAll
    public void init() {
        authorRepositoryMock = mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepositoryMock, new ModelMapper());
    }

    @Test
    @DisplayName("given Author data, when create new Author, then Author id is returned")
    void givenAuthorData_whenCreateAuthor_ThenAuthorIdReturned() {

        //given
        AuthorDto authorDto1 = new AuthorDto("test@example.com","Test","Surname");
        Author author1 = new Author();
        author1.setId(0L);    
        author1.setEmail("test@example.com");
        author1.setFirstName("Test");
        author1.setLastName("Surname");
        
        
        //when
        when(authorRepositoryMock.save(any(Author.class))).thenReturn(author1);
        Long authorId1 = authorService.create(authorDto1);

        //then
        assertNotNull(authorId1);
        assertEquals(author1.getId(), authorId1);
    }

    @Test
    @DisplayName("given Ad incomplete data, when create new Ad, then exception is thrown")
    void givenAdIncompleteData_whenCreateAd_ThenExceptionIsThrown() {

        //given
    	 AuthorDto authorDto1 = new AuthorDto("test@example.com","Test","Surname");
        
        Author author1 = new Author();
        author1.setEmail("test@example.com");
        author1.setLastName("Surname");
        
        String errorMsg = "Unable to save an incomplete entity : "+authorDto1;

        //when
        when(authorRepositoryMock.save(author1)).thenThrow(new RuntimeException(errorMsg));
        RuntimeException throwException = assertThrows(RuntimeException.class, () ->  authorService.create(authorDto1));

        // then
        assertEquals(errorMsg, throwException.getMessage());
    }

    @Test
    @DisplayName("given Author id, when delete Author, then Author is retrieved")
    void givenAuthorId_whenDeleteAuthor_ThenAuthorRetrieved() {

        //given
        long existingAuthorId = 0L;
       

        Author author1= new Author();
        author1.setId(existingAuthorId);
        author1.setEmail("test@example.com");
        author1.setFirstName("Test");
        author1.setLastName("Surname");
        
        when(authorRepositoryMock.findById(existingAuthorId)).thenReturn(Optional.of(author1));

        //when
        AuthorDto authorDto1 = authorService.delete(existingAuthorId);

        //then
        assertNotNull(authorDto1);
        assertNotNull(authorDto1.getId());
        assertEquals(author1.getId(), author1.getId());
    }

    @Test
    @DisplayName("given Author id, when delete non existing Author, then exception is thrown")
    void givenAuthorId_whenDeleteNonExistingAuthor_ThenExceptionThrown() {

        //given
        Long nonExistingAuthorId = 404L;
        String errorMsg = "Author Not Found : "+nonExistingAuthorId;
        when(authorRepositoryMock.findById(nonExistingAuthorId)).thenThrow(new EntityNotFoundException(errorMsg));

        //when
        EntityNotFoundException throwException = assertThrows(EntityNotFoundException.class, () ->  authorService.delete(nonExistingAuthorId));

        // then
        assertEquals(errorMsg, throwException.getMessage());
    }

}
