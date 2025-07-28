package com.example.demo.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * 响应包装器，用于获取响应内容
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private final ByteArrayOutputStream buffer;
    private final ServletOutputStream outputStream;
    private final PrintWriter writer;
    private final String characterEncoding;

    public ResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        // 确保使用UTF-8编码
        this.characterEncoding = "UTF-8";
        response.setCharacterEncoding(this.characterEncoding);

        buffer = new ByteArrayOutputStream();
        outputStream = new WrapperOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.characterEncoding));
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    @Override
    public String getCharacterEncoding() {
        return this.characterEncoding;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (writer != null) {
            writer.flush();
        }
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    public byte[] getContentAsByteArray() throws IOException {
        flushBuffer();
        return buffer.toByteArray();
    }

    public String getContentAsString() throws IOException {
        flushBuffer();
        return buffer.toString(this.characterEncoding);
    }

    /**
     * 将缓冲区的内容复制到原始响应
     */
    public void copyBodyToResponse() throws IOException {
        flushBuffer();
        if (buffer.size() > 0) {
            byte[] body = buffer.toByteArray();

            // 获取原始响应的输出流
            ServletOutputStream outputStream = getResponse().getOutputStream();
            outputStream.write(body);
            outputStream.flush();
        }
    }

    private class WrapperOutputStream extends ServletOutputStream {
        private final ByteArrayOutputStream outputStream;

        public WrapperOutputStream(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            outputStream.write(b, off, len);
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {
            throw new UnsupportedOperationException();
        }
    }
}

