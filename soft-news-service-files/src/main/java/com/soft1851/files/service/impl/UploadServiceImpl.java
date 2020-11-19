package com.soft1851.files.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.soft1851.files.resource.FileResource;
import com.soft1851.files.service.UploadService;
import com.soft1851.utils.extend.AliyunResource;
import lombok.RequiredArgsConstructor;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author zhao
 * @className UploadServiceImpl
 * @Description 文件上传实现
 * @Date 2020/11/19
 * @Version 1.0
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadServiceImpl implements UploadService {

    public final FastFileStorageClient fastFileStorageClient;

    public final FileResource fileResource;

    public final AliyunResource aliyunResource;

    private final Sid sid;

    @Override
    public String uploadFdfs(MultipartFile file, String fileExtName) throws Exception {
        InputStream inputStream = file.getInputStream();
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream,
                file.getSize(),
                fileExtName,
                null);
        inputStream.close();
        return storePath.getFullPath();
    }

    @Override
    public String uploadOSS(MultipartFile file, String userId, String fileExtName) throws Exception {
        String endpoint = fileResource.getEndpoint();
        String accessKeyId = aliyunResource.getAccessKeyId();
        String accessKeySecret = aliyunResource.getAccessKeySecret();

        // 创建OSSClinet实例
        OSS ossclient = new OSSClientBuilder().build(endpoint,
                accessKeyId,
                accessKeySecret);

        String fileName = sid.nextShort();
        String myObjectName = fileResource.getObjectName() + fileName + "." + fileExtName;
        // 上传网络流
        InputStream inputStream = file.getInputStream();
        ossclient.putObject(fileResource.getBucketName(),
                myObjectName,
                inputStream);
        // 关闭OSSClient
        ossclient.shutdown();
        return myObjectName;
    }
}
