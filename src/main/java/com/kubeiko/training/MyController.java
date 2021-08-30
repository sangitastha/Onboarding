package com.pets.dog;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*") //* means this request can come from any where
@RequestMapping("/v5")
@RestController
public class DogController {
    //http://localhost:8080/v5/dogs
	private List<Dog> dogs=new ArrayList<>();
	
	@PostConstruct
	public void init() {
		Dog dog1=new Dog(1,"Jp","white",23,"Abc","https://dogtime.com/assets/uploads/2011/03/puppy-development.jpg",new Date());
		 Dog dog2=new Dog(2,"ak","brown",12,"tech","https://images.unsplash.com/photo-1507146426996-ef05306b995a?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHVwcHklMjBkb2d8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80",new Date());
		 Dog dog3=new Dog(3,"jackky","red",133,"bite","https://www.bajajallianz.com/content/dam/bagic/pet-insurance/pet-dog-insurance-plan.png",new Date());
		 Dog dog4=new Dog(4,"moaoao","pink",213,"normal","https://www.adoptapet.com/blog/uploads/2018/08/sickdog.jpg",new Date());
		 dogs.add(dog1);
		 dogs.add(dog2);
		 dogs.add(dog3);
		 dogs.add(dog4);
	}
	
	
	//please call this api if search comes as query string
	// = >>>>>>>>>>> /dogs?search=james
	@GetMapping(value="/dogs",params={"search"})
	public List<Dog> searchDog(@RequestParam String search) {
		 List<Dog> searchdogs=new ArrayList<>();
		 for(Dog dog:dogs) {
			   if(dog.getName().equalsIgnoreCase(search) || dog.getColor().equalsIgnoreCase(search)) {
				   searchdogs.add(dog);
			   }
		 }
		 return searchdogs;
	}
	
	@GetMapping("/dogs")
	public List<Dog> findDogs() {
		 return dogs;
	}
	
	
////dogs/1222
	@DeleteMapping("/dogs/{did}")
	public AppResponse deleteDog(@PathVariable int did) {
		Iterator<Dog> it=dogs.iterator();
		 while(it.hasNext()) {
			 Dog dog=it.next();
			 if(dog.getDid()==did) {
				 it.remove();	 
			 }
		 }
		 AppResponse app=new AppResponse();
			app.setCode("200");
			app.setMessage("Hey! dog is deleted successfully!");
		 return app;
	}
	
	////dogs/1222
	@GetMapping("/dogs/{did}")
	public Dog getDog(@PathVariable int did) {
		 for(Dog dog:dogs) {
			   if(dog.getDid()==did) {
				   return dog;
			   }
		 }
		 return null;
	}

	
	
	@PostMapping("/dogs")
	public AppResponse postDog(@RequestBody Dog dog) {
		dogs.add(dog);
		AppResponse app=new AppResponse();
		app.setCode("200");
		app.setMessage("Hey! dog is uploaded on server");
		return app;
	}
	
}
