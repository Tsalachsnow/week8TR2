package com.example.week4tr1.repositories;

import com.example.week4tr1.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
     Optional<Contact> findById(Long contactId);

     List<Contact> findAll();

    @Modifying
    @Query("UPDATE Contact n SET n.name = ?1, n.phone = ?1, n.email = ?1, n.address = ?1, n.remark = ?1 WHERE n.contactId = ?2")
     void update(Contact c);


//    @Modifying
//    @Query()
//    List<Contact> findAll();

    @Modifying
    @Query("SELECT c FROM Contact c WHERE " +
            "lower(c.name) LIKE lower(CONCAT('%', :txt, '%')) OR " +
            "lower(c.phone) LIKE lower(CONCAT('%', :txt, '%')) OR " +
            "lower(c.email) LIKE lower(CONCAT('%', :txt, '%')) OR " +
            "lower(c.address) LIKE lower(CONCAT('%', :txt, '%')) OR " +
            "lower(c.remark) LIKE lower(CONCAT('%', :txt, '%'))")
    List<Contact> findUserContact(@Param("txt") String txt);

    @Modifying
    @Query("DELETE FROM Contact WHERE contactId = ?1")
    void deleteAll(Contact cotactIds);
}
