package dummy.jpa;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
public class ReplicateApplication  {

    public ReplicateApplication() {
    }


    @Bean
    public CommandLineRunner hello(ReleveRepo repo) {

	//	Releve rel = new Releve();
	//	rel.setType("Elec");
	//	Random rand = new Random();
	//	rel.setIndex(rand.nextInt(100000));
	//	DecimalFormat df = new DecimalFormat("00");
	//	rel.setDate("201511" + df.format(rand.nextInt(31)));
	//	repo.save(rel);


	ExecutorService executor = Executors.newCachedThreadPool();
	for(int i=0;i<3;i++) {
	    executor.execute(new Runnable() {

		@Override
		public void run() {
		    Releve rel = new Releve();
		    rel.setType("Elec");
		    Random rand = new Random();
		    rel.setIndex(rand.nextInt(100000));
		    DecimalFormat df = new DecimalFormat("00");
		    rel.setDate("201511" + df.format(rand.nextInt(31)));
		    repo.save(rel);		
		}
	    });
	}
	for(int i=0;i<2;i++) {
	    executor.execute(new Runnable() {

		@Override
		public void run() {
		    Releve rel = new Releve();
		    rel.setType("Elec");
		    Random rand = new Random();
		    int j= rand.nextInt(100000);
		    rel.setIndex(j);
		    DecimalFormat df = new DecimalFormat("00");
		    String dt = "201511" + df.format(rand.nextInt(31));
		    rel.setDate(dt);
		    repo.save(rel);
		    repo.delete(rel);
		}
	    });
	}	
	executor.shutdown();


	return (args) -> {
	    repo.findAll().forEach(
		    r->
		    System.err.println(String.format(
			    "%s, %s, %s", r.getDate(), r.getType(), r.getIndex() 
			    ))
		    );
	};

    }

    public static void main(String[] args) {			
	SpringApplication.run(ReplicateApplication.class, args);
	//	ConfigurableApplicationContext context = SpringApplication.run(ReplicateApplication.class, args);
    }

}
