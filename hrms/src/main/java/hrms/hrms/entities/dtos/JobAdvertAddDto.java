package hrms.hrms.entities.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertAddDto {

	private int cityId;
	private LocalDate deadline;
	private String description;
	private int employerId;
	private int jobPositionId;
	private boolean isOpen;
	private int OpenPositionCount;
	private LocalDate publishedAt;
	private int salaryMax;
	private int salaryMin;
    private int workPlaceId;
    private int workTimeId;
	
}
