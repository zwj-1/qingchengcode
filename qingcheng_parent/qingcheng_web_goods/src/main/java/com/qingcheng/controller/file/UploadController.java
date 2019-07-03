package com.qingcheng.controller.file;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.internal.OSSBucketOperation;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {


    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/native")
    public String nativeUpload(@RequestParam("file") MultipartFile file) {
        String path = request.getSession().getServletContext().getRealPath("img");
        String filePath = path + "/" + file.getOriginalFilename();
        File desFile = new File(filePath);
        if (!desFile.getParentFile().exists()) {
            desFile.mkdirs();
        } else {
            try {
                file.transferTo(desFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "http://localhost:9101/img/" + file.getOriginalFilename();
    }

    //阿里云上传
    @Autowired
    private OSSClient ossClient;

    @RequestMapping("/oss")
    public String ossUpload(@RequestParam("file") MultipartFile file, String folder) {
        String bucketName = "qingchengcode-zwj";
        String filename = folder + "/" + UUID.randomUUID() + file.getOriginalFilename();
        try {
            ossClient.putObject(bucketName, filename, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "https://" + bucketName + ".oss-cn-chengdu.aliyuncs.com/" + filename;
    }
}
