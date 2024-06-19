package org.example.storespring.repository;


import org.example.storespring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query( value = "SELECT u FROM user u WHERE u.id = ?1",nativeQuery = true)
    User findById(String id);
    @Query( value = "SELECT u FROM user u WHERE u.email = ?1",nativeQuery = true)
    User findByEmail(String id);
    @Query(value="select * from user u where u.id = ?1 and u.role = ?2",nativeQuery = true)
    User findByIdAndRole(String id, String role);
    void deleteById(String id);

}
