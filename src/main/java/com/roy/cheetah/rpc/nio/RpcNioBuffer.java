package com.roy.cheetah.rpc.nio;

import com.roy.cheetah.rpc.constants.Constants;
import com.roy.cheetah.rpc.utils.ArraysUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author:Roy
 * @Date: Created in 13:49 2017/10/15 0015
 */
public class RpcNioBuffer {
    private byte[] buf;
    private int readIndex;
    private int writeIndex;

    public RpcNioBuffer() {

    }

    public RpcNioBuffer(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative initial size: " + size);
        }
        buf = new byte[size];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - buf.length > 0) {
            grow(minCapacity);
        }
    }

    public void write(byte b[], int off, int len) {
        if ((off < 0) || (off > b.length) || (len < 0) || ((off + len) - b.length > 0)) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(writeIndex + len);
        System.arraycopy(b, off, buf, writeIndex, len);
        writeIndex += len;
    }

    public void write(byte b[]) {
        write(b, 0, b.length);
    }

    public void reset() {
        writeIndex = 0;
    }

    public byte toByteArray()[] {
        return ArraysUtils.copyOfRange(buf, readIndex, writeIndex);
    }

    @Override
    public String toString() {
        return new String(buf, readIndex, writeIndex);
    }

    public synchronized int size() {
        return writeIndex - readIndex;
    }

    public void compact() {
        if (readIndex > 0) {
            for (int i = readIndex;i < writeIndex;i++) {
                buf[i - readIndex] = buf[i];
            }
            writeIndex = writeIndex - readIndex;
            readIndex = 0;
        }
    }

    public boolean hasRpcObject() {
        if (writeIndex - readIndex >= Constants.RPC_PROTOCOL_HEAD_LEN) {
            byte[] lenbuf = new byte[4];
            System.arraycopy(buf, readIndex + 16, lenbuf, 0, 4);
            int len =
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = buf.length;
        int newCapacity = oldCapacity << 1;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity < 0) {
            if (minCapacity < 0)
                throw new OutOfMemoryError();
            newCapacity = Integer.MAX_VALUE;
        }
       buf = ArraysUtils.copyOf(buf, newCapacity);
    }
}
