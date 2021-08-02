package hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import hrms.hrms.business.abstracts.UserService;
import hrms.hrms.core.utilities.results.DataResult;
//import kodlamaio.hrms.core.utilities.results.Result;
import hrms.hrms.entities.concretes.User;
import hrms.hrms.entities.dtos.UserLoginDto;
import hrms.hrms.entities.dtos.UserLoginReturnDto;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	@PostMapping("/login")
    ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto){
        DataResult<UserLoginReturnDto> result = this.userService.login(userLoginDto);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.badRequest().body(result);
        }
    }
	
//	@PostMapping("/add")
//	public Result add(@RequestBody User user){
//		return this.userService.add(user);
//	}
//	
//	@PostMapping("/update")
//	public Result update(@RequestBody User user){
//		return this.userService.update(user);
//	}
//	
//	@PostMapping("/delete")
//	public Result delete(@RequestParam("id") int id){
//		return this.userService.delete(id);
//	}
//	
//	@GetMapping("/getbyid")
//	public DataResult<User> getById(@RequestParam("id") int id){
//		return this.userService.getById(id);
//	}
//	
	@GetMapping("/getall")
	public DataResult<List<User>> getAll(){
		return this.userService.getAll();
	}
	
}