package com.shivam.talk.repository;

import com.shivam.talk.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TextRepository extends JpaRepository<Text, Long> {

    public List<Text> findAllByReviever(String reviever);


    public List<Text> findAllByRevieverAndReceivedTime(String reviever, Date receivedTime);

}
