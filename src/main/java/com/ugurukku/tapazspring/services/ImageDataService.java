package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.exceptions.user.entities.ImageData;
import com.ugurukku.tapazspring.repositories.ImageDataRepository;
import com.ugurukku.tapazspring.utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;

    public ImageDataService(ImageDataRepository imageDataRepository) {
        this.imageDataRepository = imageDataRepository;
    }

    public void uploadImage(Long id,MultipartFile file) throws IOException {

        imageDataRepository.save(
                new ImageData(id,
                file.getContentType(),
                ImageUtil.compressImage(file.getBytes())));


    }

    @Transactional
    public ImageData getInfoByProductId(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findImageDataByProductId(id);

        return new ImageData(
                dbImage.get().getProductId(),
                dbImage.get().getType(),
                ImageUtil.decompressImage(dbImage.get().getImageData()));

    }

    @Transactional
    public byte[] getImage(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findImageDataByProductId(id);
        return ImageUtil.decompressImage(dbImage.get().getImageData());
    }

}
