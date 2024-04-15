package com.yhq.sensitive.converter;

import com.yhq.sensitive.core.SensitiveWrapper;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author chench
 * @date 2024.04.12
 */
public class SensitiveGsonMessageConverter extends MappingJackson2HttpMessageConverter
        implements SensitiveConverter, SensitiveWrapper {

    public SensitiveGsonMessageConverter() {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Object sensitive = sensitive(object);
        super.writeInternal(sensitive, type, outputMessage);
    }
}