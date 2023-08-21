package com.example.repo;

import static com.example.domain.Tables.*;
import static org.jooq.impl.DSL.*;

import org.jooq.DSLContext;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Foo;
import com.example.domain.tables.records.BarRecord;
import com.example.domain.tables.records.FooRecord;

public class FooRepository {
   private static final Name ID_NAME = DSL.name( "id" );
   private final DSLContext dslContext;

   public FooRepository( final DSLContext dslContext ) {
      this.dslContext = dslContext;
   }

   @Transactional
   public Foo save( final Foo entity ) {
      final FooRecord newRecord = dslContext.newRecord( FOO );
      newRecord.from( entity );
      newRecord.store();
      final Long id = newRecord.getValue( ID_NAME, Long.class );
      entity.setId( id );

      entity.getBars().forEach( bar -> {
         final BarRecord barRecord = dslContext.newRecord( BAR );
         barRecord.from( bar );
         barRecord.store();
         final Long barId = barRecord.getValue( ID_NAME, Long.class );
         bar.setId( barId );
      } );

      return entity;
   }

   @Transactional( readOnly = true )
   public Foo getId( final Long id ) {
      return
            dslContext.select(
                        asterisk(),
                        multiset(
                              select( asterisk() )
                                    .from( BAR )
                                    .where( BAR.FOO_ID.eq( FOO.ID ) )
                        ).as( "bars" )
                  )
                  .from( FOO )
                  .where( FOO.ID.eq( id ) )
                  .fetchOne( this::createEntity );
   }

   public Foo createEntity( final Record tableRecord ) {
      return tableRecord.into( Foo.class );
   }

}
