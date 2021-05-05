package com.demo.repository;

import com.demo.Authentication.LogOutList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LogOutListRepository extends JpaRepository<LogOutList,Integer> {

    @Query(value = "select * from Log_out_list where Logged_Out_Token=?",nativeQuery = true)
    LogOutList findByBlackListedToken(String token);

    @Modifying
    @Transactional
    @Query(value = "delete from Log_out_list where username=?",nativeQuery = true)
    void deleteByUsername(String username);
}
