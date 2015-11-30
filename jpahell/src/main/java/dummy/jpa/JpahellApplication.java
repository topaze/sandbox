package dummy.jpa;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import dummy.jpa.model.Releve;
import dummy.jpa.repo.ReleveRepo;

@SpringBootApplication
@ComponentScan("dummy.jpa.repo")
@ImportResource( { "classpath:spring/applicationContext.xml" } )
public class JpahellApplication {

    public static void main(String[] args) {
	SpringApplication.run(JpahellApplication.class, args);
    }

    @Bean
    public CommandLineRunner hello(ReleveRepo repo) {

	Releve rel = new Releve();
	rel.setType("Elec");
	Random rand = new Random();
	rel.setIndex(rand.nextInt(100000));
	DecimalFormat df = new DecimalFormat("00");
	rel.setDate("201511" + df.format(rand.nextInt(31)));
	repo.save(rel);

	return (args) -> {
	    repo.findAll().forEach(
		    r->
		    System.err.println(String.format(
			    "%s, %s, %s", r.getDate(), r.getType(), r.getIndex() 
			    ))
		    );
	};
	
    }

}
