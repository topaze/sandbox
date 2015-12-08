package dummy.jpa.listener;

import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AfterBurnerListener implements PostInsertEventListener, PostCommitInsertEventListener {

    private static final long serialVersionUID = -92057775084297482L;


    @Override
    public void onPostInsert(PostInsertEvent event) {
	ObjectMapper mapper = new ObjectMapper();
	try {	    

	    Object entity = event.getEntity();
	    String status = event.getSession().getTransaction().getLocalStatus().name();
	    int hashCode = event.getSession().getTransaction().hashCode();


	    System.err.println(
		    String.format("%d:%s:%s",
			    hashCode(),
			    mapper.writeValueAsString(status),
			    mapper.writeValueAsString(entity)
			    )
		    );
	    
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister arg0) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent event) {
	// TODO Auto-generated method stub
    }

}
