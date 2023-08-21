package com.example.domain;

import java.util.Set;

public class Foo extends AbstractJooqBaseEntity {

   private String name;

   private Set<Bar> bars;

   public void setName( final String name ) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public Set<Bar> getBars() {
      return bars;
   }

   public void setBars( final Set<Bar> bars ) {
      this.bars = bars;
      bars.forEach( bar -> bar.setFoo( this ) );
   }
}
