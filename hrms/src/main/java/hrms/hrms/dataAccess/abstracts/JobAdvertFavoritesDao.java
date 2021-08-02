package hrms.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.hrms.entities.concretes.JobAdvertFavorites;

public interface JobAdvertFavoritesDao extends JpaRepository<JobAdvertFavorites, Integer>{
	
	List<JobAdvertFavorites> findByJobseekerId(int id);
    boolean existsByJobseeker_IdAndJobAdvert_Id(int jobseekerId,int JobAdvertId);

}
