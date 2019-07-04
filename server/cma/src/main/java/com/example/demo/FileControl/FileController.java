package com.example.demo.FileControl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
//import com.example.demo.FileControl.FileUploadConfig;
import com.example.demo.framework.Response;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class FileController {
    /**
     * 单文件上传
     * @param file
     * @param request
     * @return response
     * 本地路径为String path="F:/img/upload/"（统一）
     */
    public Response upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,String saveFileName) {
        Response response=new Response();

        if (!file.isEmpty()) {
            String path="F:/img/upload/";
            File saveFile = new File(path + saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            System.out.println(ServletFileUpload.isMultipartContent(request));
            try {

                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                response.code=200;
                response.msg=saveFile.getName()+"上传成功";
                response.data=null;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                response.code=500;
                response.msg="上传失败";
            } catch (IOException e) {
                e.printStackTrace();
                response.code=500;
                response.msg="上传失败";
            }
        } else {
            response.code=500;
            response.msg="上传失败,文件为空";
        }
        return response;
    }

    /**
     * 多文件上传
     *
     * @param
     * @return
     */
 /*  @PostMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request) throws IOException {
        File savePath = new File(request.getSession().getServletContext().getRealPath("/upload/"));
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    File saveFile = new File(savePath, file.getOriginalFilename());
                    stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    if (stream != null) {
                        stream.close();
                        stream = null;
                    }
                    return "第 " + i + " 个文件上传有错误" + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件为空";
            }
        }
        return "所有文件上传成功";
    }*/

    public String downloadFile( HttpServletResponse response,String fileName) {
       // Response myresponse=new Response();
        if (fileName != null) {
            //设置文件路径
            String realPath = "F:/img/upload/";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println("成功");
        return null;
    }
    public void deletefile(String filename,String path){
        System.out.println(path+filename);
        File file=new File(path+filename);
        if(file.exists())
            System.out.println("!!!!!!!!!!!!!!!!!");
        deletef(file);
    }
    private static void deletef(File file) {
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
        } else {
            // 首先得到当前的路径
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deletef(childFile);
            }
            file.delete();
        }
    }
    public String  getsuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        return suffix;
    }

}
