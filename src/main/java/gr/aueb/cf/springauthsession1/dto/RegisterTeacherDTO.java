package gr.aueb.cf.springauthsession1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RegisterTeacherDTO {

	@NotNull
	@Size(min = 3, max = 16)
	private String username;

	@Pattern(regexp = "^.{4,}$", message = "Password must contain at least 4 characters.")
	private String password;

	@NotNull
	@Size(min = 3, max = 16, message = "Firstname must contain at least 3 characters.")
	private String firstname;

	@NotNull
	@Size(min = 3, max = 16, message = "Lastname must contain at least 3 characters.")
	private String lastname;
}
