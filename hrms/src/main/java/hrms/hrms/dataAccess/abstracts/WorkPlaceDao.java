package hrms.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;


import hrms.hrms.entities.concretes.WorkPlace;

public interface WorkPlaceDao extends JpaRepository<WorkPlace,Integer>{
	WorkPlace getById(int id);
}
