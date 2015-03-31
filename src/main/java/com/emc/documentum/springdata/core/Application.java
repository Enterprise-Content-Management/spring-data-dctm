package com.emc.documentum.springdata.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.documentum.fc.common.DfException;

@SpringBootApplication
@ComponentScan("com.emc.documentum.springdata")
//@EnableAspectJAutoProxy
public class Application {
  
  public static void main(String[] args) throws DfException {
    SpringApplication.run(Application.class);
  }
}
