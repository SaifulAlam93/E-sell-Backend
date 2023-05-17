package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}
