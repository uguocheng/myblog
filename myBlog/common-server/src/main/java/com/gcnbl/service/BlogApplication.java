package com.gcnbl.service;

import com.gcnbl.annotation.BlogScan;
import lombok.SneakyThrows;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

public class BlogApplication implements ImportBeanDefinitionRegistrar {
    private static Class<?> primarySource;
    private static String[] scanPackage;

    public BlogApplication() {
    }

    public static Class<?> getPrimarySource() {
        return primarySource;
    }

    public static String[] getScanPackage() {
        return scanPackage;
    }

    @SneakyThrows
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        try {
            primarySource = ClassUtils.forName(importingClassMetadata.getClassName(), ClassUtils.getDefaultClassLoader());
            BlogScan blogScan = (BlogScan)primarySource.getAnnotation(BlogScan.class);
            if (blogScan.value().length == 0) {
                if (primarySource.getPackage().getName().startsWith("com.gcnbl")) {
                    scanPackage = new String[1];
                    scanPackage[0] = "com.gcnbl";
                } else {
                    scanPackage = new String[2];
                    scanPackage[0] = primarySource.getPackage().getName();
                    scanPackage[1] = "com.gcnbl";
                }
            } else {
                scanPackage = blogScan.value();
            }

        } catch (Throwable var4) {
            throw var4;
        }
    }
}