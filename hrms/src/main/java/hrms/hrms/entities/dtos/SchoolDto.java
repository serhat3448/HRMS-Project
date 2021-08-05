package hrms.hrms.entities.dtos;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolDto {
	
	private int Jobseeker_id;
	private String schoolName;
	private String department;
	private LocalDate startAt;
	private LocalDate endAt;

}
