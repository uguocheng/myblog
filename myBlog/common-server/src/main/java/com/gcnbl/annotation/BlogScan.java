package com.gcnbl.annotation;

import com.gcnbl.service.BlogApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({BlogApplication.class})
@Comment("Erupt项目包扫描核心注解")
public @interface BlogScan {
    @Comment("需要被扫描的包名")
    String[] value() default {};
}
