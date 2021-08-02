package hrms.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.hrms.business.abstracts.UserService;
import hrms.hrms.core.utilities.results.DataResult;
import hrms.hrms.core.utilities.results.ErrorDataResult;
import hrms.hrms.core.utilities.results.SuccessDataResult;
import hrms.hrms.core.utilities.results.Result;
import hrms.hrms.core.utilities.results.SuccessResult;
import hrms.hrms.dataAccess.abstracts.UserDao;
import hrms.hrms.dataAccess.abstracts.JobseekerDao;
import hrms.hrms.dataAccess.abstracts.EmployerDao;
import hrms.hrms.dataAccess.abstracts.EmployeeDao;
import hrms.hrms.entities.concretes.User;
import hrms.hrms.entities.dtos.UserLoginDto;
import hrms.hrms.entities.dtos.UserLoginReturnDto;


@Service
public class UserManager implements UserService{

	private UserDao userDao;
    private JobseekerDao jobseekerDao;
    private EmployerDao employerDao;
    private EmployeeDao employeeDao;
	
	@Autowired
	public UserManager(UserDao userDao, JobseekerDao jobseekerDao, EmployerDao employerDao, EmployeeDao employeeDao) {
		super();
		this.userDao = userDao;
		this.employeeDao = employeeDao;
		this.employerDao = employerDao;
		this.jobseekerDao = jobseekerDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
	    return new SuccessResult("User has been added.");
	}
//
//	@Override
//	public Result update(User user) {
//		this.userDao.save(user);
//      return new SuccessResult("User has been updated.");
//	}
//
//	@Override
//	public Result delete(int id) {
//		this.userDao.deleteById(id);
//      return new SuccessResult("User has been deleted.");
//	}
//
//	@Override
//	public DataResult<User> getById(int id) {
//		return new SuccessDataResult<User>(this.userDao.getById(id));
//	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll());
	}

	@Override
	public DataResult<User> getByEmail(String email) {

		return new SuccessDataResult<User>(this.userDao.findUserByEmail(email));
	}

	@Override
	public DataResult<UserLoginReturnDto> login(UserLoginDto userLoginDto) {
        User user = this.userDao.findUserByEmail(userLoginDto.getEmail());
        if(user==null){
            return new ErrorDataResult<UserLoginReturnDto>("Hatalı email girdiniz");
        }else if(!user.getPassword().equals(userLoginDto.getPassword())){
            return new ErrorDataResult<UserLoginReturnDto>("Hatalı şifre girdiniz");
        }
        //}else if(!user.isMailVerify()){
        //    return new ErrorDataResult<UserLoginReturnDto>("Giriş yapmak için email onayı yapmanız gerekmektedir");
        //}
            //DB değiştirilecek.
        UserLoginReturnDto userLoginReturnDto = new UserLoginReturnDto();
        userLoginReturnDto.setId(user.getId());
        userLoginReturnDto.setEmail(user.getEmail());

        if(this.jobseekerDao.existsById(user.getId())){
            userLoginReturnDto.setUserType(1);
            userLoginReturnDto.setName(this.jobseekerDao.getById(user.getId()).getFirstName()+" "+this.jobseekerDao.getById(user.getId()).getLastName());
        }else if(this.employerDao.existsById(user.getId())){
            userLoginReturnDto.setUserType(2);
            userLoginReturnDto.setName(this.employerDao.getById(user.getId()).getCompanyName());
        }else if(this.employeeDao.existsById(user.getId())){
            userLoginReturnDto.setUserType(3);
            userLoginReturnDto.setName(this.employeeDao.getById(user.getId()).getFirstName()+" "+this.employeeDao.getById(user.getId()).getLastName());
        }else {
            return new ErrorDataResult<UserLoginReturnDto>("Bir hata oluştu");
        }

        return new SuccessDataResult<UserLoginReturnDto>(userLoginReturnDto,"Giriş yapıldı");
    }

}