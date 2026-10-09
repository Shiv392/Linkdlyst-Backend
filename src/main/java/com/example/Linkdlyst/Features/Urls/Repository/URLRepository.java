package com.example.Linkdlyst.Features.Urls.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;

@Repository 
public interface URLRepository extends JpaRepository<UrlEntiry, Long> {
    
    Optional<UrlEntiry> findByUser_IdAndUrl(Long userId, String url);

    //_ is saying that User_id is a property of the UserEntity class which is a property of the UrlEntiry class
    Page<UrlEntiry> findByUser_IdOrderByUpdatedAtDesc(Long userId, Pageable pagable);

    Optional<UrlEntiry> findByUser_IdAndShortCode(Long userId, String shortCode);

    Optional<UrlEntiry> findByIdAndUser_Id(Long id, Long userId);
}
