package com.soft1851.api.controller.files;

import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhao
 * @className FileUploadControllerApi
 * @Description 文件上传api接口
 * @Date 2020/11/19
 * @Version 1.0
 **/
@Api(value = "文件上传controller",tags = {"文件上传controller"})
@RequestMapping("fs")
public interface FileUploadControllerApi {
    /**
     * 上传用户头像
     * @param userId 用户id
     * @param file 文件对象
     * @return 封装结果
     * @throws Exception 异常
     */
    @ApiOperation(value = "上传用户头像",notes = "上传用户头像",httpMethod = "POST")
    @PostMapping("uploadFace")
    GraceResult uploadFile(@RequestParam String userId, MultipartFile file) throws Exception;

    /**
     * 上传多个文件
     * @param userId 用户id
     * @param files 文件数组
     * @return 返回
     * @throws Exception 异常
     */
    @PostMapping("/uploadSomeFiles")
    GraceResult uploadSomeFiles(@RequestParam String userId,
                                MultipartFile[] files) throws Exception;


    /**
     * 管理员人脸入库
     * @param multipartFile 人脸照片文件
     * @return GraceResult
     * @throws Exception 异常
     */
    @ApiOperation(value = "管理员人脸入库",notes = "管理员人脸入库",httpMethod = "POST")
    @PostMapping("uploadToGridFS")
    GraceResult uploadToGridFs(@RequestParam String username, @RequestParam(value = "file") MultipartFile multipartFile) throws Exception;

    /**
     * 从gridFS读取文件
     *
     * @param faceId 人脸id
     * @param request 请求
     * @param response 响应
     * @throws Exception 异常
     */
    @ApiOperation(value = "从gridFS读取文件",notes = "从gridFS读取文件",httpMethod = "GET")
    @GetMapping("/readInGridFS")
    GraceResult readInGridFs(@RequestParam String faceId, HttpServletRequest request,
                             HttpServletResponse response) throws Exception;
}
