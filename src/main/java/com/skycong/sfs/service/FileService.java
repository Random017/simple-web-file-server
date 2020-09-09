package com.skycong.sfs.service;

import com.skycong.sfs.dao.model.FileModel;
import com.skycong.sfs.dao.repository.FileRepository;
import com.skycong.sfs.util.Util;
import com.skycong.sfs.util.qrcode.code.QRCodeMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author ruanmingcong 2019.12.3 11:35
 */
@Service
public class FileService {

    @Value("${diskPath}")
    private String DISK_PATH;


    @Value("${fileDomain}")
    private String FILE_DOMAIN;

    @Value("${fileDirType}")
    private Integer fileDirType;


    private static final String SEPARATOR = File.separator;

    @Autowired
    private FileRepository fileRepository;

    /**
     * 保存单文件上传
     */
    public Object updateOneFile(MultipartFile file) throws IOException {
        String fileMD5 = Util.getFileMD5(file.getInputStream());
        FileModel byMd5 = fileRepository.findByMd5(fileMD5);
        if (byMd5 != null) {
            return byMd5;
        }
        FileModel fileModel;
        try {
            fileModel = saveNetFileToLocal(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filename = fileModel.getFilename();
        if (filename.length() >= 100) {
            filename = filename.substring(0, 100);
        }
        fileModel.setFilename(filename);
        fileModel.setMd5(fileMD5);
        fileModel.setQrCode(saveQrCode(fileModel.getUrl(), filename));
        fileModel.setCreateAt(System.currentTimeMillis());
        fileRepository.save(fileModel);
        return fileModel;
    }

    /**
     * 生成并保存文件URL 二维码
     */
    private String saveQrCode(String content, String filename) {
        String encode = QRCodeMain.encode(content, DISK_PATH + "qrcode", filename);
        String s = encode.substring(DISK_PATH.length());
        return convertLocalPath2HttpUrl(FILE_DOMAIN + s);
    }


    public Object findByCondition(Integer page) {
        Sort id = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, 200, id);
        return fileRepository.findAll(pageable);
    }

    public Object deleteOne(Integer fid) {
        Optional<FileModel> byId = fileRepository.findById(fid);
        FileModel fileModel = byId.orElse(null);
        if (fileModel == null) {
            return "no such file";
        }
        fileRepository.deleteById(fid);
        try {
            Files.deleteIfExists(Paths.get(fileModel.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileModel;
    }

    /**
     * 保存网路上传的文件到本地
     *
     * @param file 将要保存的文件
     * @throws Exception
     */
    private FileModel saveNetFileToLocal(MultipartFile file) throws Exception {
        String uploadFileName = file.getOriginalFilename();
        String dir;
        if (fileDirType == 1) {
            dir = FileTypeEnum.getFileTypeStr(uploadFileName).toLowerCase() + SEPARATOR;
        } else {
            dir = generateFileDir();
        }
        File directory = new File(DISK_PATH + dir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(Objects.requireNonNull(directory.list())));
        String newFileName = Util.checkAndGenerateFilename(strings, uploadFileName);
        String saveFileName = dir + newFileName;
        File saveFile = new File(DISK_PATH + saveFileName);
        if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
        file.transferTo(saveFile);
        FileModel save = new FileModel();
        save.setFilename(newFileName);
        save.setType(FileTypeEnum.getFileTypeStr(uploadFileName).toLowerCase());
        // save.setMd5(Util.getFileMD5(new FileInputStream(saveFile)));
        save.setPath(saveFile.getAbsolutePath());
        save.setUrl(convertLocalPath2HttpUrl(FILE_DOMAIN + saveFileName));
        save.setLength(file.getSize());
        return save;
    }

    /**
     * 生成文件目录，
     *
     * @return eg： 2018/9/23/11/
     */
    private static String generateFileDir() {
        LocalDateTime now1 = LocalDateTime.now();
        int year = now1.getYear();
        int monthValue = now1.getMonthValue();
        int dayOfMonth = now1.getDayOfMonth();
        int hour = now1.getHour();
        return year + SEPARATOR + monthValue + SEPARATOR + dayOfMonth + SEPARATOR + hour + SEPARATOR;
    }

    /**
     * 本地文件路径转化为 http url
     */
    private static String convertLocalPath2HttpUrl(String localPath) {
        return localPath.replaceAll("\\\\", "/");
    }

}