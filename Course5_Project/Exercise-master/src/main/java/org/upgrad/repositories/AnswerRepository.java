package org.upgrad.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Answer;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,String>{

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="insert into answer (ans,date ,user_id,question_id) values (?1,CURRENT_TIMESTAMP ,?2,?3)")
    void addAnswerToQuestionById(String answer, int userId, int questionId);

    @Query(nativeQuery = true,value="select user_id from answer where id = ?1")
    int getUserByAnswerId(int answerId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="update answer set ans=?2,modifiedon=now() where id=?1")
    void editAnswerById(int answerId , String answer);

    @Query(nativeQuery = true,value ="select * from answer where question_id = ?1")
    List<Answer> getAllAnswersByQuestionId(int questionId);

    @Query(nativeQuery = true,value ="select * from answer where user_id = ?1")
    List<Answer> getAllAnswersByUserId(int userId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true,value="delete from answer where id=?1 ")
    void deleteAnswerByAnswerId(int answerId);
}