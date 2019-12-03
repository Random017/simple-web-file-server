package com.skycong.sfs.dao.repository;

import com.skycong.sfs.dao.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ruanmingcong 2019.12.3 11:34
 */
public interface FileRepository   extends JpaRepository<FileModel,Integer> {

    FileModel findByMd5(String md5);
}
