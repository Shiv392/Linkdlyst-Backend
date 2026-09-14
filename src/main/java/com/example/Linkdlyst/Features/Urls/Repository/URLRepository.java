package com.example.Linkdlyst.Features.Urls.Repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;

@Repository 
public interface URLRepository extends JpaRepository<UrlEntiry, Long> {
    
    Optional<UrlEntiry> findByUser_IdAndUrl(Long userId, String url);

    //_ is saying that User_id is a property of the UserEntity class which is a property of the UrlEntiry class
    List<UrlEntiry> findByUser_Id(Long userId);

    Optional<UrlEntiry> findByUser_IdAndShortCode(Long userId, String shortCode);

    Optional<UrlEntiry> findByIdAndUser_Id(Long id, Long userId);
}
