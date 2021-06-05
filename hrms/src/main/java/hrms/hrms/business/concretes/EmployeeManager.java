package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.EmployeeService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
//import hrms.hrms.core.utilities.results.Result;
//import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.EmployeeDao;
import hrms.hrms.entities.concretes.Employee;


@Service
public class EmployeeManager implements EmployeeService{

	private EmployeeDao employeeDao;
	
	@Autowired
	public EmployeeManager(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

//	@Override
//	public Result add(Employee employee) {
//		this.employeeDao.save(employee);
//      return new SuccessResult("Employee has been added.");		
//	}
//
//	@Override
//	public Result update(Employee employee) {
//		this.employeeDao.save(employee);
//      return new SuccessResult("Employee has been updated.");
//	}
//
//	@Override
//	public Result delete(int id) {
//		this.employeeDao.deleteById(id);
//      return new SuccessResult("Employee has been deleted.");
//	}
//
//	@Override
//	public DataResult<Employee> getById(int id) {
//		return new SuccessDataResult<Employee>(this.employeeDao.getById(id));
//	}

	@Override
	public DataResult<List<Employee>> getAll() {
		// TODO Auto-generated method stub
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll());
	}

}