package hrms.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.SchoolForCV;
import hrms.hrms.entities.dtos.SchoolDto;

public interface SchoolForCVDao extends JpaRepository<SchoolForCV, Integer>{
	SchoolForCV getById(int id);
	List<SchoolForCV> getAllByJobseeker_idOrderByEndAtDesc(int id);
	List<SchoolForCV> getAllByJobseeker_id(int id);
}