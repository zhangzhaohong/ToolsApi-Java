package com.koala.service.data.redis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
    public static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception ignored) {

        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return ois.readObject();
        } catch (Exception ignored) {

        }
        return null;
    }
}
