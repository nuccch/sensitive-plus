package com.yhq.sensitive.converter;

import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

/**
 * @author chench
 * @date 2024.04.12
 */
public interface SensitiveConverter {
    /** 支持的Content-Type类型 */
    List<MediaType> supportedMediaTypes = Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8);
}