package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",
            //베이스를 지정하지않을 시 Configuration클래스 패키지부터 시작 (hello.core)
            //권장방법: 설정 정보 클래스의 위치를 프로젝트 최상단에 두기 
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
