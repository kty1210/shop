package com.shop.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception{

        //난수 발생 - 파일명
        UUID uuid = UUID.randomUUID();
        //확장자 자르기
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        //난수(파일명) + 확장자
        String saveFileName = uuid.toString() + extension;
        String fileUploadFullUrl = uploadPath + "/" + saveFileName;

        //FileOutputStream 파일 생성 (이미 존재한다면 파일 열어줌)
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        //파일 저장
        fos.write(fileData);
        //파일 닫기
        fos.close();

        return saveFileName;

    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);

        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        } else {
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
