package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface BlogRepository extends JpaRepository<Blogs,Long> {

}
