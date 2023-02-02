package com.ugurukku.tapazspring.repositories;

import com.ugurukku.tapazspring.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData,Long> {

        Optional<ImageData> findImageDataByProductId(Long id);

}
