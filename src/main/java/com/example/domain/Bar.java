package com.example.domain;

public class Bar extends AbstractJooqBaseEntity {

   private String name;
   private Foo foo;

   public void setName( final String name ) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   protected void setFoo( final Foo foo ) {
      this.foo = foo;
   }

   public Long getFooId() {
      return foo.getId();
   }
}
