package com.bing.funread.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Description:图片工具类
 * Author: zhangfusheng
 * Date: 2018/3/30 下午3:58
 */
public class ImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);

    private static final int WIDTH = 700;

    /**
     * 按默认宽度及原图片宽、高比例压缩
     * @param source
     * @param targetFilePath
     */
    public static void reduceImage(File source, String targetFilePath) {
        reduceImage(source, targetFilePath, WIDTH);
    }

    /**
     * 指定图片宽度，按原始图片宽、高比例对图片进行压缩
     *
     * @param source 源图片文件
     * @param targetFilePath 目标图片地址
     * @param width 压缩后图片的宽度
     */
    public static void reduceImage(File source, String targetFilePath, int width) {
        try {
            //获得源图片的宽高存入数组中
            ImageSize imageSize = getImageSize(source);
            if (imageSize == null || imageSize.isEmpty()) {
                return;
            }
            // 开始读取文件并进行压缩
            Image src = ImageIO.read(source);

            int height = imageSize.getHeight() * width / imageSize.getWidth();

            // 构造一个类型为预定义图像类型之一的 BufferedImage
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //绘制图像  getScaledInstance表示创建此图像的缩放版本，返回一个新的缩放版本Image,按指定的width,height呈现图像
            //Image.SCALE_SMOOTH,选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

            // 创建文件目录
            File targetFile = new File(targetFilePath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            //创建文件输出流
            FileOutputStream out = new FileOutputStream(targetFile);
            //将图片按JPEG压缩，保存到out中
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            //关闭文件输出流
            out.close();
        } catch (Exception e) {
            logger.error("图片压缩出错，文件地址：{}，原因：{}", source.getPath(), e);
        }
    }

    private static ImageSize getImageSize(File file) {
        try (InputStream is = new FileInputStream(file)) {
            // 从流里将图片写入缓冲图片区
            BufferedImage src = ImageIO.read(is);
            return new ImageSize(src.getWidth(null), src.getHeight(null));
        } catch (Exception e) {
            logger.error("获取图片长度、宽度出错：{}", e);
        }
        return null;
    }

    static class ImageSize {
        private int width;

        private int height;

        public ImageSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public boolean isEmpty() {
            return width == 0 || height == 0;
        }
    }

//    public static void main(String[] args) {
//        File srcfile = new File("/Users/finup/Desktop/2.jpg");
//        File distfile = new File("/Users/finup/Desktop/aa.jpg");
//
//        System.out.println("压缩前图片大小：" + srcfile.length());
//        reduceImage(srcfile, "/Users/finup/Desktop/aa/aa.jpg", WIDTH);
//        System.out.println("压缩后图片大小：" + distfile.length());
//    }
}
