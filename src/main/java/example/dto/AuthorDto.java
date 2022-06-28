package example.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AuthorDto {

    private Long id;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "An alphanumeric first name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "An alphanumeric last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "A valid mail address is mandatory")
    private String email;

	public AuthorDto(String email, String firstName, String lastName) {
		this.email =email;
		this.firstName =firstName;
		this.lastName = lastName;
	}
	
	public AuthorDto(String email, String lastName) {
		this.email =email;

		this.lastName = lastName;
	}

	public AuthorDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDto other = (AuthorDto) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
