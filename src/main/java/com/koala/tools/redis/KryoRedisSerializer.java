package com.koala.tools.redis;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.Objects;

@Slf4j
public class KryoRedisSerializer<T> implements RedisSerializer<T> {

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private final Kryo kryo = new Kryo();

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (Objects.isNull(t)) return EMPTY_BYTE_ARRAY;
        byte[] buffer = new byte[10240];
        try (Output output = new Output(buffer)) {
            kryo.writeClassAndObject(output, t);
            return output.toBytes();
        } catch (Exception e) {
            log.error("writeClassAndObject Error", e);
        }
        return EMPTY_BYTE_ARRAY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes) || bytes.length == 0) return null;
        try (Input input = new Input(bytes)) {
            return (T) kryo.readClassAndObject(input);
        } catch (Exception e) {
            log.error("readClassAndObject Error", e);
        }
        return null;
    }
}
