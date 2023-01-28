package springboot.repo;

import org.springframework.data.repository.CrudRepository;

import springboot.model.Student;

public interface StudenRepo extends CrudRepository<Student, Integer> {

}
