package dummy.jpa.interceptor;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SafInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 4013707250670795688L;

    @Override
    public void postFlush(Iterator entities) {
	String mtd =  new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	int i=0;	
	while(entities.hasNext()) {
	    Object e = entities.next();
	    try {
		System.err.println(
			String.format("%s(%d):%s", 
				mtd,
				i++,
				mapper.writeValueAsString(e)
				)
			);
	    } catch (JsonProcessingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	}


	super.postFlush(entities);
    }



    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s:%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(entity),
			    mapper.writeValueAsString(state),
			    mapper.writeValueAsString(types)
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	//	inserts.add(entity);

	return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s:%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(entity),
			    mapper.writeValueAsString(state),
			    mapper.writeValueAsString(types)
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s:%s:%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(entity),
			    mapper.writeValueAsString(previousState),
			    mapper.writeValueAsString(currentState),
			    mapper.writeValueAsString(types)
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }



    @Override
    public void afterTransactionBegin(Transaction tx) {
	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(tx)			  
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	super.afterTransactionBegin(tx);
    }



    @Override
    public void afterTransactionCompletion(Transaction tx) {
	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(tx)			  
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	super.afterTransactionCompletion(tx);
    }



    @Override
    public void beforeTransactionCompletion(Transaction tx) {

	String mtd = new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	try {
	    System.err.println(
		    String.format("%s:%s", 
			    mtd, 
			    mapper.writeValueAsString(tx)			  
			    )
		    );
	} catch (JsonProcessingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	super.beforeTransactionCompletion(tx);
    }




}
