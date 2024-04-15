package com.yhq.sensitive.converter;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yhq.sensitive.core.SensitiveWrapper;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

/**
 * @author chench
 * @date 2024.04.12
 */
public class SensitiveFastJsonMessageConverter extends FastJsonHttpMessageConverter
        implements SensitiveConverter, SensitiveWrapper {

    public SensitiveFastJsonMessageConverter() {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Object sensitive = sensitive(object);
        super.writeInternal(sensitive, outputMessage);
    }
}