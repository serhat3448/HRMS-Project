package hrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.Jobseeker;

public interface JobseekerDao extends JpaRepository<Jobseeker, Integer> {

	Jobseeker findJobseekerByNationalId(String nationalId);
	Jobseeker getById(int id);
	Jobseeker findByEmail(String email);
	Jobseeker findById(int id);
	
}