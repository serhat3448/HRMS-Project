package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.CityService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.CityDao;
import hrms.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService{

	private CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}
	
	@Override
	public Result add(City city) {
		this.cityDao.save(city);
	    return new SuccessResult("City has been added.");
	}

	@Override
	public Result update(City city) {
		this.cityDao.save(city);
	    return new SuccessResult("City has been updated.");
	}

	@Override
	public Result delete(int id) {
		this.cityDao.deleteById(id);
	    return new SuccessResult("City has been deleted.");
	}

//	@Override
//	public DataResult<City> getById(int id) {
//		return new SuccessDataResult<City>(this.cityDao.getById(id));
//	}

	@Override
	public DataResult<List<City>> getAll() {
		return new SuccessDataResult<List<City>>(this.cityDao.findAll());
	}

}