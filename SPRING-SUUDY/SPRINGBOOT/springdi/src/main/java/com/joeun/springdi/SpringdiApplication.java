package com.joeun.springdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.joeun.springdi.bean.Test;

// alt + shift + O : 전체 import
@SpringBootApplication
public class SpringdiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringdiApplication.class, args);

        // IoC 컨테이너에 등록된 모든 빈의 이름 조회
        String[] allBeanNames = context.getBeanDefinitionNames();
        // 조회된 빈 이름 출력
        for (String beanName : allBeanNames) {
            System.out.println("빈 이름: " + beanName);
        }

        // getBean() : 빈 이름으로 빈 객체를 가져옴
        Test test = context.getBean(Test.class);
        test.print();
    }
}