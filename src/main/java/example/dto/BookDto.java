package example.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class BookDto {

    private Long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Genre is mandatory")
    private String genre;

    private BigDecimal price;

    @NotNull(message = "Author is mandatory")
    private AuthorDto author;

	public BookDto(Long id,String  description, String genre, String  title, BigDecimal price, AuthorDto defaultAuthor) {
		this.id =id;
		this.description = description;
		this.genre = genre;
		this.title =title;
		this.price =price;
		this.author = defaultAuthor;
	}
	
	public BookDto(String  description, String genre, String  title, BigDecimal price, AuthorDto defaultAuthor) {
	
		this.description = description;
		this.genre = genre;
		this.title =title;
		this.price =price;
		this.author = defaultAuthor;
	}
	
	public BookDto(Long id,String  description, String genre, String  title, BigDecimal price) {
		this.id =id;
		this.description = description;
		this.genre = genre;
		this.title =title;
		this.price =price;
	
	}
	
	public BookDto(String  description, String genre, String  title, BigDecimal price) {
		this.id =id;
		this.description = description;
		this.genre = genre;
		this.title =title;

	
	}

	public BookDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public AuthorDto getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDto author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, description, genre, id, price, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDto other = (BookDto) obj;
		return Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& Objects.equals(genre, other.genre) && Objects.equals(id, other.id)
				&& Objects.equals(price, other.price) && Objects.equals(title, other.title);
	}
    
    
}
