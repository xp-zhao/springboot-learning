package com.xp.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

/**
 * Created by xp-zhao on 17-9-24.
 */
public class RandomAccessFileTest {

    private static final Logger logger     = Logger.getLogger(RandomAccessFileTest.class);

    private static final String ENCODING   = "UTF-8";
    private static final int    NUM        = 50000;

    private static       File   file       = new File(ClassLoader.getSystemResource("").getPath() + File.separator + "test.txt");
    private static       File   randomFile = new File(ClassLoader.getSystemResource("").getPath() + File.separator + "RandomFile.txt");
    private static       File   playlist   = new File("D:\\migu-userGroup\\data\\org\\playlistinfo.txt");

    /**
     * 生成1000w随机文本文件
     */
    @Test
    public void makePin() {
        String prefix = "_$#";
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file, true), ENCODING);
            // 随机生成数据
            for (int j = 0; j < 400000000; j++) {
                out.write(prefix + (int) (130000000 * Math.random()) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
        logger.info(file.getAbsolutePath());
    }

    /**
     * 测试RandomAccessFile读取文件
     */
    @Test
    public void testRandomAccessRead() {
        long start = System.currentTimeMillis();
//
        logger.info(String.valueOf(file.exists()));
        long pos = 0L;
        while (true) {
            Map<String, Object> res = FileUtil.readLine(playlist, ENCODING, pos, NUM);
            // 如果返回结果为空结束循环
            if (MapUtils.isEmpty(res)) {
                break;
            }
            Object po = res.get("pins");
            List<String> pins = (List<String>) res.get("pins");
            if (CollectionUtils.isNotEmpty(pins)) {
//                logger.info(Arrays.toString(pins.toArray()));
                if (pins.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            pos = (Long) res.get("pos");
        }
        logger.info(((System.currentTimeMillis() - start) / 1000) + "");
    }

    /**
     * 测试BufferedRandomAccessFile读取文件
     */
    @Test
    public void testBufferedRandomAccessRead() {
        long start = System.currentTimeMillis();

        file = new File("D:\\migu-userGroup\\data\\org\\test.txt");
        logger.info(String.valueOf(file.exists()));
        long pos = 0L;
        int count = 0;
        while (true) {
            Map<String, Object> res = FileUtil.BufferedRandomAccessFileReadLine(file, ENCODING, pos, NUM);
            // 如果返回结果为空结束循环
            if (MapUtils.isEmpty(res)) {
                break;
            }
            List<String> pins = (List<String>) res.get("pins");
            count = count + Integer.parseInt(res.get("count").toString());
            if (CollectionUtils.isNotEmpty(pins)) {
                if (pins.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            pos = (Long) res.get("pos");
        }
        logger.info(((System.currentTimeMillis() - start) / 1000) + ": "+count);
    }

    /**
     * 测试普通读取文件
     */
    @Test
    public void testCommonRead() {
        long start = System.currentTimeMillis();
        logger.info(String.valueOf(randomFile.exists()));
        int index = 0;
        while (true) {
            List<String> pins = FileUtil.readLine(playlist, ENCODING, index, NUM);
            if (CollectionUtils.isNotEmpty(pins)) {
//                logger.info(Arrays.toString(pins.toArray()));
                if (pins.size() < NUM) {
                    break;
                }
            } else {
                break;
            }
            index += NUM;
        }
        logger.info(((System.currentTimeMillis() - start) / 1000) + "");
    }
}
