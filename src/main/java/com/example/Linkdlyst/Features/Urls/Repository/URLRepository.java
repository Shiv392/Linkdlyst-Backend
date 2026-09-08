package com.example.Linkdlyst.Features.Urls.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Linkdlyst.Features.Urls.Entities.UrlEntiry;

@Repository 
public interface URLRepository extends JpaRepository<UrlEntiry, Long> {
    
    Optional<UrlEntiry> findByUserIdAndUrl(Long userId, String url);
}
