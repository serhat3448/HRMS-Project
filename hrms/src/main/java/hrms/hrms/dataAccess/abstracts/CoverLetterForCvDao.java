package hrms.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.CoverLetterForCV;

public interface CoverLetterForCvDao extends JpaRepository<CoverLetterForCV, Integer>{
	CoverLetterForCV getById(int id);
}