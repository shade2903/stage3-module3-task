package com.mjc.school;

import com.mjc.school.config.AppConfig;
import com.mjc.school.controller.view.Menu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class
        Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Menu menu = applicationContext.getBean(Menu.class);
        menu.menuScreen();


    }
}
