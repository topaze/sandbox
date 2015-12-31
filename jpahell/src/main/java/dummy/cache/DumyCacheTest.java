package dummy.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages={"dummy.cache"})
@Component("test")
public class DumyCacheTest {

    @Autowired DummyService service;

    public DumyCacheTest() {
	super();
    }


    public void test() {
	System.err.println("O " + service.toto("20151231"));
	System.err.println("A " + service.tata("20151231"));
	System.err.println("U " + service.tutu("20151231", "waldo"));
	for(int i=0;i<5;i++) {
	    System.err.println("O " + service.toto("20151231"));
	    System.err.println("A " + service.tata("20151231"));
	    System.err.println("U " + service.tutu("20151231", "waldo"));
	}
	System.err.println("U " + service.tutu("20151231", "charly"));
	System.err.println("A " + service.tata("20160101"));
	System.err.println("A " + service.tata("20153112"));
    }

    public void test2() {	
	System.err.println("A " + service.tata("20160101"));
	System.err.println("A " + service.tata("20153112"));
	System.err.println("A " + service.tata("20160101"));
	System.err.println("A " + service.tata("20153112"));
	System.err.println("A " + service.tata("20160101"));
	System.err.println("A " + service.tata("20151231"));
    }

    public static void main(String[] args) {
	ApplicationContext context = SpringApplication.run(DumyCacheTest.class, args);
	DumyCacheTest test = context.getBean("test", DumyCacheTest.class);
	test.test();
	System.err.println("-----"); 
	test.test2();
    }

}

