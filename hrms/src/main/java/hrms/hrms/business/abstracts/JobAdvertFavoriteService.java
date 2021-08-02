package hrms.hrms.business.abstracts;

import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.JobAdvertFavorites;

import java.util.List;

public interface JobAdvertFavoriteService {
    public DataResult<List<JobAdvertFavorites>> getByJobseekerId(int jobseekerId);
    public Result addFavorite(int jobseekerId, int jobAdvertId);
    public Result removeFavorite(int favoriteId);
}