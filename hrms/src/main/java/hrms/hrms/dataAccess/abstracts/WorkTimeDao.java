package hrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.WorkTime;

public interface WorkTimeDao extends JpaRepository<WorkTime, Integer>{
	WorkTime getById(int id);
}
