package com.example.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.RepositoryConfiguration;
import com.example.domain.Bar;
import com.example.domain.Foo;

@ExtendWith( SpringExtension.class )
@SpringBootTest(
      classes = { RepositoryConfiguration.class }, webEnvironment = SpringBootTest.WebEnvironment.NONE )
@ActiveProfiles( "test" )
@EnableAutoConfiguration
public class FooRepositoryTest {

   @Autowired
   private FooRepository fooRepository;

   @Test
   void testSelect() {
      final Foo foo = new Foo();
      foo.setName( "Test" );

      final Bar bar1 = new Bar();
      bar1.setName( "bar1" );

      final Bar bar2 = new Bar();
      bar2.setName( "bar2" );

      foo.setBars( Set.of( bar1, bar2 ) );

      final Foo savedFoo = fooRepository.save( foo );

      final Foo getFoo = fooRepository.getId( savedFoo.getId() );

      assertThat( savedFoo ).isEqualTo( getFoo );
   }

}
