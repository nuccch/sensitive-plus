package com.yhq.sensitive.converter;

import com.yhq.sensitive.core.SensitiveWrapper;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.io.Writer;
import java.lang.reflect.Type;

/**
 * @author chench
 * @date 2024.04.12
 */
public class SensitiveGsonMessageConverter extends GsonHttpMessageConverter
        implements SensitiveConverter, SensitiveWrapper {

    public SensitiveGsonMessageConverter() {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Override
    protected void writeInternal(Object object, Type type, Writer writer) throws Exception {
        Object sensitive = sensitive(object);
        super.writeInternal(sensitive, type, writer);
    }
}