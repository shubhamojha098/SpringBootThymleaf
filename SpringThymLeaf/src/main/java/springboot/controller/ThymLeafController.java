package springboot.controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import springboot.model.Student;
import springboot.repo.StudenRepo;

@Controller
public class ThymLeafController {
	@Autowired
	private StudenRepo sRepo;
	
	@GetMapping("/")
	public String home() {
		return "result";
	}
	@GetMapping("/local")
	public String localhome() {
		return "result";
	}
	@GetMapping("/list")
	public ModelAndView showList() {
		ModelAndView mv=new ModelAndView("ShowStudent");
		Iterable<Student>it=sRepo.findAll();
		Iterator<Student>itr=it.iterator();
		List<Student>data=new LinkedList<Student>();
		while(itr.hasNext()) {
			data.add(itr.next());
			mv.addObject("data",data);
		}
		return mv;
	}
	
	
	 @GetMapping("/form")
	    public ModelAndView createProjectForm() {
	        ModelAndView mv= new ModelAndView("form");
	      mv.addObject("student", new Student());
	        return mv;
	    }
	 
	/*
	 @ModelAttribute("student") public Student newStudent() { return newStudent();
	 }
	 */

	  @PostMapping("/addstudent") 
	  public String save(@ModelAttribute Student student)
	  { 
		  sRepo.save(student); 
		  return "redirect:/list";
	  }
	  
	  @GetMapping("/delete/{sid}")
	  public String deleteStudent(@PathVariable("sid") Integer sid) {
		  System.out.println(sid);
		  sRepo.deleteById(sid);
		  return "redirect:/list";
	  }

}
