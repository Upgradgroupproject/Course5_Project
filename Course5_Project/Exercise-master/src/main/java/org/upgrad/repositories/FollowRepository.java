package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Follow;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FollowRepository extends CrudRepository<Follow,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into follow (user_id,category_id) values (?1,?2)")
    void followCategory(int userId, int categoryId);


    @Query(nativeQuery = true,value="SELECT id from follow where user_id = ?1")
    int findUserInFollowedByList(int userId);

    @Modifying
    @Query(nativeQuery = true,value="delete from follow where id=?1")
    void unfollowCategory(int followId);





}
