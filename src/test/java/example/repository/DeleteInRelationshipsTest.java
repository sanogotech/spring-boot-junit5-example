package example.repository;

import example.model.Book;
import example.model.Author;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("IntegrationTest")
@DisplayName("Delete in Relationships Integration Tests")
public class DeleteInRelationshipsTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author1;
    private Author author2;
    private long authorInitialCount;

    private Book book1;
    private Book book2;
    private long bookInitialCount;

    @BeforeEach
    public void init() {

        authorInitialCount = authorRepository.count();

    	Author mb1 = new Author();
    	mb1.setEmail("test1@example.com");
    	mb1.setFirstName("Test1");
    	mb1.setLastName("Surname1");
        
        author1 = authorRepository.save(mb1);

        Author mb2 = new Author();
    	mb2.setEmail("test1@example.com");
    	mb2.setFirstName("Test1");
    	mb2.setLastName("Surname1");
       
        author2 = authorRepository.save(mb2);

        book1 = new Book();
        book1.setTitle("test1");
        book1.setDescription("example1");
        book1.setGenre("example1");
        book1.setPrice(BigDecimal.TEN);
        book1.setAuthor(author1);
        

        book2 = new Book();
        book2.setTitle("test2");
        book2.setDescription("example2");
        book2.setGenre("example2");
        book2.setPrice(BigDecimal.TEN);
        book2.setAuthor(author2);
  
        bookInitialCount = bookRepository.count();
        bookRepository.saveAll(Arrays.asList(book1, book2));
    }

    @AfterEach
    public void teardown() {
        bookRepository.deleteAll(Arrays.asList(book1, book2));
        authorRepository.deleteAll(Arrays.asList(author1, author2));
    }

    @Test
    @DisplayName("when deleting Authors, then Books should be deleted too")
    public void whenDeletingAuthors_thenBooksShouldAlsoBeDeleted() {

        authorRepository.delete(author1);
        authorRepository.delete(author2);

        assertEquals(bookInitialCount, bookRepository.count());
        assertEquals(authorInitialCount, authorRepository.count());
    }

    @Test
    @DisplayName("when deleting Books, then Author should not be deleted")
    public void whenDeletingBooks_thenAuthorShouldNotBeDeleted() {

        //given
        long authorCount = authorRepository.count();
        bookRepository.deleteAll(Arrays.asList(book1, book2));

        assertEquals(bookInitialCount, bookRepository.count());
        assertEquals(authorCount, authorRepository.count());
    }
}
