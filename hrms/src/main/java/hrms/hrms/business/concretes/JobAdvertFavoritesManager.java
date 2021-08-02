package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hrms.hrms.business.abstracts.JobAdvertFavoriteService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.ErrorResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.dataAccess.abstracts.JobAdvertDao;
import hrms.hrms.dataAccess.abstracts.JobAdvertFavoritesDao;
import hrms.hrms.dataAccess.abstracts.JobseekerDao;
import hrms.hrms.entities.concretes.JobAdvertFavorites;

public class JobAdvertFavoritesManager implements JobAdvertFavoriteService{
	
    private JobAdvertFavoritesDao jobAdvertFavoritesDao;
    private JobseekerDao jobseekerDao;
    private JobAdvertDao jobAdvertDao;
    
    @Autowired
    public JobAdvertFavoritesManager(JobAdvertFavoritesDao jobAdvertFavoritesDao,JobseekerDao jobseekerDao, JobAdvertDao jobAdvertDao) {
        this.jobAdvertFavoritesDao = jobAdvertFavoritesDao;
        this.jobseekerDao=jobseekerDao;
        this.jobAdvertDao=jobAdvertDao;
    }

	@Override
	public DataResult<List<JobAdvertFavorites>> getByJobseekerId(int jobseekerId) {
		if(!this.jobseekerDao.existsById(jobseekerId)){
            return new ErrorDataResult<List<JobAdvertFavorites>>("Böyle bir kullanıcı yok");
        }
        return new SuccessDataResult<List<JobAdvertFavorites>>(this.jobAdvertFavoritesDao.findByJobseekerId(jobseekerId),"Data listelendi");
	}

	@Override
	public Result addFavorite(int jobseekerId, int jobAdvertId) {
		if(!this.jobseekerDao.existsById(jobseekerId)){
            return new ErrorResult("Böyle bir kullanıcı yok");
        }else if(!this.jobAdvertDao.existsById(jobAdvertId)){
            return new ErrorResult("Böyle bir ilan yok");
        }else if(this.jobAdvertFavoritesDao.existsByJobseeker_IdAndJobAdvert_Id(jobseekerId,jobAdvertId)){
            return new ErrorResult("Bu ilan zaten favorilerinizde");
        }

        JobAdvertFavorites jobAdFavorites=new JobAdvertFavorites();
        jobAdFavorites.setJobseeker(this.jobseekerDao.getById(jobseekerId));
        jobAdFavorites.setJobAdvert(this.jobAdvertDao.getById(jobAdvertId));
        this.jobAdvertFavoritesDao.save(jobAdFavorites);
        return new SuccessResult("İlan favorilere eklendi");
	}

	@Override
	public Result removeFavorite(int favoriteId) {
		if(!this.jobAdvertFavoritesDao.existsById(favoriteId)){
            return new ErrorResult("Böyle bir favori ilan yok");
        }
        this.jobAdvertFavoritesDao.deleteById(favoriteId);
        return new SuccessResult("İlan favorilerden kandırıldı");
	}

}
