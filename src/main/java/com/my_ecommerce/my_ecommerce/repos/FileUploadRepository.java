package com.my_ecommerce.my_ecommerce.repos;

import com.my_ecommerce.my_ecommerce.domain.FileUpload;
import com.my_ecommerce.my_ecommerce.domain.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {


    @Query(value = " SELECT * FROM file_upload fu LEFT JOIN product_file pf ON fu.id = pf.file_upload_id WHERE pf.products_id = :products_id ", nativeQuery = true)
    Set<FileUpload> getAllByProductId(@Param(value = "products_id") Long id);
}
