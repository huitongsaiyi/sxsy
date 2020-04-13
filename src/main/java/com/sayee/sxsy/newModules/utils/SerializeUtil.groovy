package com.sayee.sxsy.newModules.utils
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author zhaoshuya* @date 2020/4/8 10:10
 */
class SerializeUtil {
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            byte[] bytes = bos.toByteArray();
            //            set(key.getBytes(), str);
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输出流
            closeOS(oos, bos);
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(new BufferedInputStream(bis));
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭输入流
            closeIS(ois, bis);
        }
        return obj;
    }

    /*
     *关闭输出流
     */
    private static void closeOS(ObjectOutputStream oos, ByteArrayOutputStream bos) {
        try {
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *关闭输入流
     */
    private static void closeIS(ObjectInputStream ois, ByteArrayInputStream bis) {
        try {
            ois.close();
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
