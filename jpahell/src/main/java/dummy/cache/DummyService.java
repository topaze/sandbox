package dummy.cache;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("service")
public class DummyService {

    @Cacheable(cacheNames="dummy", keyGenerator="DummyKeyGenerator")
    public String toto(String date) {
	String res = date;
	System.err.println("\ttoto : " + date + " >> " + res); 
	return res;  
    }

    @Cacheable(cacheNames="dummy", keyGenerator="DummyKeyGenerator")
    public String tata(String date) {
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	String res = date;
	try {
	    res = sdf2.format(sdf1.parse(date));
	} catch (ParseException e) {
	    System.err.println(e.getLocalizedMessage());
	}
	System.err.println("\ttata : " + date + " >> " + res); 
	return res;  
    }
    
    @Cacheable(cacheNames="dummy", keyGenerator="DummyKeyGenerator")
    public String tutu(String date, String nom) {
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	String res = date;
	try {
	    res = sdf2.format(sdf1.parse(date)) + ", " + nom;
	} catch (ParseException e) {
	    System.err.println(e.getLocalizedMessage());
	}
	System.err.println("\ttutu : " + date + " >> " + res); 
	return res;  
    }
    
}

