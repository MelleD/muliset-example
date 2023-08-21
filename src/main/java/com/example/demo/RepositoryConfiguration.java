/*
 * Copyright (c) 2022 Robert Bosch Manufacturing Solutions GmbH, Germany. All rights reserved.
 */

package com.example.demo;

import org.jooq.DSLContext;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.repo.FooRepository;

@Configuration
@AutoConfigureAfter( DataSourceAutoConfiguration.class )
public class RepositoryConfiguration {

   @Bean
   FooRepository fooRepository( final DSLContext dslContext ) {
      return new FooRepository( dslContext );
   }
}
