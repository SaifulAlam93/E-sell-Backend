package com.my_ecommerce.my_ecommerce.service;

import com.my_ecommerce.my_ecommerce.domain.FileUpload;
import com.my_ecommerce.my_ecommerce.domain.Products;
import com.my_ecommerce.my_ecommerce.model.FileUploadDTO;
import com.my_ecommerce.my_ecommerce.repos.FileUploadRepository;
import com.my_ecommerce.my_ecommerce.repos.ProductsRepository;
import com.my_ecommerce.my_ecommerce.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class FileUploadService {
    private final String FOLDER_PATH = "I://my-ecommerce/Angular/my-ecommerce/src/assets/img/uploadedImage/";

    private final FileUploadRepository fileUploadRepository;
    private final ProductsRepository productsRepository;

    public FileUploadService(final FileUploadRepository fileUploadRepository,
                             final ProductsRepository productsRepository) {
        this.fileUploadRepository = fileUploadRepository;
        this.productsRepository = productsRepository;
    }

    public List<FileUploadDTO> findAll() {
        final List<FileUpload> fileUploads = fileUploadRepository.findAll(Sort.by("id"));
        return fileUploads.stream()
                .map((fileUpload) -> mapToDTO(fileUpload, new FileUploadDTO()))
                .toList();
    }

    public FileUploadDTO get(final Long id) {
        return fileUploadRepository.findById(id)
                .map(fileUpload -> mapToDTO(fileUpload, new FileUploadDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final FileUploadDTO fileUploadDTO) {
        final FileUpload fileUpload = new FileUpload();
        mapToEntity(fileUploadDTO, fileUpload);
        return fileUploadRepository.save(fileUpload).getId();
    }

    public void update(final Long id, final FileUploadDTO fileUploadDTO) {
        final FileUpload fileUpload = fileUploadRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(fileUploadDTO, fileUpload);
        fileUploadRepository.save(fileUpload);
    }

    public void delete(final Long id) {
        fileUploadRepository.deleteById(id);
    }

    private FileUploadDTO mapToDTO(final FileUpload fileUpload, final FileUploadDTO fileUploadDTO) {
        fileUploadDTO.setId(fileUpload.getId());
        fileUploadDTO.setName(fileUpload.getName());
        fileUploadDTO.setType(fileUpload.getType());
        fileUploadDTO.setUrl(fileUpload.getUrl());
        fileUploadDTO.setImageNo(fileUpload.getImageNo());
        fileUploadDTO.setImageType(fileUpload.getImageType());
        return fileUploadDTO;
    }

    private FileUpload mapToEntity(final FileUploadDTO fileUploadDTO, final FileUpload fileUpload) {
        fileUpload.setName(fileUploadDTO.getName());
        fileUpload.setType(fileUploadDTO.getType());
        fileUpload.setUrl(fileUploadDTO.getUrl());
        fileUpload.setImageNo(fileUploadDTO.getImageNo());
        fileUpload.setImageType(fileUploadDTO.getImageType());

        return fileUpload;
    }


    public Set<Long> fileUpload(Long id, MultipartFile[] file) {
        Optional<Products> product = productsRepository.findById(id);
        if (product.isPresent()) {
            Set<Long> fileUploadIDs = new HashSet<>();
            Set<FileUpload> fileUploads = new HashSet<>();
//            Set<FileUpload> fileUploads = product.get().getFileUploads()!=null? product.get().getFileUploads():new HashSet<>();
            try {
                int index = 0;
                for (MultipartFile mf : file) {
                    String filePath = FOLDER_PATH + mf.getOriginalFilename();

                    FileUpload fileUpload = fileUploadRepository.save(
                            FileUpload.builder()
                                    .name(mf.getOriginalFilename())
                                    .type(mf.getContentType())
                                    .imageNo(index++)
                                    .imageType("test")
                                    .url(filePath).build());

                    mf.transferTo(new File(filePath));
                    if (fileUpload != null) {
                        fileUploadIDs.add(fileUpload.getId());
                        fileUploads.add(fileUpload);
                    }
                }
            } catch (Exception e) {
            }
            product.get().setFileUploads(fileUploads);
//            product.get().setFileUploads(mergeSet(fileUploads,product.get().getFileUploads()));
            productsRepository.save(product.get());
            return fileUploadIDs;
        }
        return  null;
    }
}
