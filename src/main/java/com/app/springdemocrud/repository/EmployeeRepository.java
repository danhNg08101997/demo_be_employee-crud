package com.app.springdemocrud.repository;

import com.app.springdemocrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {
    @Query("select e from Employee e where e.emailId = :emailId")
    Employee findByEmailId(@Param("emailId") String emailId);
}
