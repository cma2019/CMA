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
     * 服务器主路径为String path="E:/CMA/FileSystem/"（统一）
     * 分支路径由String dir确定
     */
    /*File pathTest=new File(".");
    {
        try{
            String pathString=pathTest.getCanonicalPath();//再用字符串操作拼接上后面的路径"\server\FileSystem
            System.out.println("test path");
            System.out.println(pathString);
            System.out.println("test path over");
        }catch(IOException e){
            e.printStackTrace();
        }
    }*/
    String path="E:/CMA/FileSystem/";
    public Response upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,String saveFileName,String dir) {
        Response response=new Response();
        if (!file.isEmpty()) {
            String filepath=path+dir+"/";
            File saveFile = new File(filepath + saveFileName);
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

    /**
     * 单文件下载
     * @param response
     * @param fileName
     * @param dir
     * @return
     */
    public String downloadFile( HttpServletResponse response,String fileName,String dir) {
       // Response myresponse=new Response();
        if (fileName != null) {
            //设置文件路径
            String filepath=path+dir+"/";
            System.out.println(filepath);
            System.out.println(fileName);
            File file = new File(filepath, fileName);
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
        return null;
    }
    public boolean deletefile(String filename,String dir){
        System.out.println(path+dir+"/"+filename);
        File file=new File(path+dir+"/"+filename);
        if(file.exists())
            System.out.println("!!!!!!!!!!!!!!!!!");
        return  deletef(file);
    }
    private static boolean deletef(File file) {
        if (file.isFile()) {// 表示该文件不是文件夹
            file.delete();
            System.out.println("已删除");
            return true;
        } else {
            // 首先得到当前的路径
            if(!file.exists()){
                return false;
            }
            String[] childFilePaths = file.list();
            for (String childFilePath : childFilePaths) {
                File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
                deletef(childFile);
            }
            file.delete();
        }
        return false;
    }
    public String  getsuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(suffix);
        return suffix;
    }

}
