package com.skycong.sfs.dao.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ruanmingcong 2019.12.3 11:27
 */
@Entity
@Table(name = "tb_simple_web_file")
@DynamicInsert
@DynamicUpdate
public class FileModel implements Serializable {


    private static final long serialVersionUID = -6187444298403648815L;
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

    /**
     * 二维码
     */
    private String qrCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FileModel{");
        sb.append("id=").append(id);
        sb.append(", filename='").append(filename).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", md5='").append(md5).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", path='").append(path).append('\'');
        sb.append(", length=").append(length);
        sb.append(", qrCode=").append(qrCode);
        sb.append('}');
        return sb.toString();
    }
}