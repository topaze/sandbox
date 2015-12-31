package dummy.cache;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("DummyKeyGenerator")
public class DummyKeyGenerator implements KeyGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyKeyGenerator.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object generate(Object target, Method method, Object... params) {
	String jparams = "";
	try {
	    jparams = mapper.writeValueAsString(params);
	} catch (JsonProcessingException e) {
	    LOGGER.error(e.getLocalizedMessage(), e);
	}

	return sha256(method.getName() + jparams);
    }

    public static String sha256(String input){
	String sha256 = null;
	MessageDigest md = null;
	try {
	    md = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
	    LOGGER.error(e.getLocalizedMessage(), e);
	}

	if(md!=null) {
	    try {
		md.update(input.getBytes("UTF-8"));
	    } catch (UnsupportedEncodingException e) {
		LOGGER.error(e.getLocalizedMessage(), e);
	    }
	    sha256 = new String(Base64.getEncoder().encode(md.digest()));
	}
	return sha256;

    }

}
