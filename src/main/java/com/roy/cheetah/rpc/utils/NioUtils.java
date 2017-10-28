package com.roy.cheetah.rpc.utils;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Author:Roy
 * @Date: Created in 14:33 2017/10/15 0015
 */
public class NioUtils {

    private final static Logger logger = Logger.getLogger(NioUtils.class);

    public final static int RPC_PROTOCOL_HEAD_LEN = 20;

    public static byte[] zip(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gos = null;
            try {
                gos = new GZIPOutputStream(bos);
                gos.write(bytes);
                return bos.toByteArray();
            } catch (IOException e) {
                logger.error("NioUtils occurs ERROR: ", e);
            } finally {
                if (gos != null) {
                    try {
                        gos.close();
                    } catch (IOException e) {
                        logger.error("NioUtils occurs ERROR: ", e);
                    }
                }
            }
        }
        return new byte[0];
    }

    public static byte[] unzip(byte[] bytes) {
        GZIPInputStream gis = null;
        ByteArrayOutputStream bos = null;
        try {
            gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
            bos = new ByteArrayOutputStream();
            byte[] buff = new byte[512];
            int read = gis.read(buff);
            while (read > 0) {
                bos.write(buff, 0,read);
                read = gis.read(buff);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            logger.error("NioUtils occurs ERROR: ", e);
        } finally {
            if (gis != null) {
                try {
                    gis.close();
                } catch (IOException e) {
                    logger.error("NioUtils occurs ERROR: ", e);
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    logger.error("NioUtils occurs ERROR: ", e);
                }
            }
        }
        return new byte[0];
    }
}
