/*
 * Copyright (c) 2022 Robert Bosch Manufacturing Solutions GmbH, Germany. All rights reserved.
 */

package com.example.domain;

import java.util.Objects;

public abstract class AbstractJooqBaseEntity {

   private Long id;
   
   public Long getId() {
      return id;
   }

   public void setId( final Long id ) {
      this.id = id;
   }

   @Override
   public boolean equals( final Object o ) {
      if ( this == o ) {
         return true;
      }
      if ( !(o instanceof final AbstractJooqBaseEntity that) ) {
         return false;
      }

      if ( getId() == null ) {
         return false;
      }

      return Objects.equals( id, that.id );
   }

   @Override
   public int hashCode() {
      if ( getId() == null ) {
         return super.hashCode();
      }
      return Objects.hash( id );
   }

   @Override
   public String toString() {
      return "AbstractJooqBaseEntity{" +
            "id=" + id +
            "} " + super.toString();
   }

}
