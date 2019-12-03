package com.skycong.sfs.dao.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author ruanmingcong 2019.12.3 11:27
 */
@Entity
@Table(name = "tb_simple_web_file")
@DynamicInsert
@DynamicUpdate
@Data
@ToString
public class FileModel {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 文件名
     */
    @Column(length = 100)
    private String filename;

    /**
     * 文件类型 1：image，2：audio，3:video，4：text  5：application
     */
    @Column(length = 15)
    private String type;

    /**
     * 文件MD5
     */
    @Column(length = 32, unique = true)
    private String md5;

    private String url;

    private String path;

    private Long length;
}