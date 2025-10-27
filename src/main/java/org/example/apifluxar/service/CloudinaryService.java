package org.example.apifluxar.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.commons.io.FilenameUtils;
import org.example.apifluxar.dto.cloud.CloudinayUploadResponse;
import org.example.apifluxar.util.FileUpdateUtil;
import org.hibernate.query.sqm.produce.function.FunctionArgumentException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public CloudinayUploadResponse upload(MultipartFile file, String folder) {
        FileUpdateUtil.assertAllowed(file, FileUpdateUtil.IMAGE_PATTERN);

        String baseName = FilenameUtils.getBaseName(file.getOriginalFilename());
        String fileName = FileUpdateUtil.getFileName(baseName);

        String publicId = (folder == null || folder.isBlank()) ? fileName : folder + "/" + fileName;

        // 4) opções do upload
        Map<String, Object> options = ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );

        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), options);

            String url = (String) uploadResult.get("secure_url");
            String returnedPublicId = (String) uploadResult.get("public_id");
            Integer width = (Integer) uploadResult.get("width");
            Integer height = (Integer) uploadResult.get("height");

            return new CloudinayUploadResponse(url, returnedPublicId, width, height);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar o arquivo para o Cloudinary", e);
        } catch (FunctionArgumentException fae) {
            throw fae;
        }
    }
}
