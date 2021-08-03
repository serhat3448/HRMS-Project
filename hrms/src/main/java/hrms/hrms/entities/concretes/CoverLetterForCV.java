package hrms.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cv_cover_letters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","jobseeker"})
public class CoverLetterForCV{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	
	@Column(name = "content")
	private String content;
	
	public CoverLetterForCV(String content, int jobseekerId) {
		super();
		this.content = content;
		this.jobseeker.setId(jobseekerId);
	}

	@ManyToOne()
	@JoinColumn(name = "jobseeker_id")
	@JsonIgnore
	private Jobseeker jobseeker;
	
}