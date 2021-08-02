package hrms.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForRegisterDto {
	
	private String companyName;
	private String phoneNumber;
	private String email;
	private String password;
	private String rePassword;
	private String webSite;
}
