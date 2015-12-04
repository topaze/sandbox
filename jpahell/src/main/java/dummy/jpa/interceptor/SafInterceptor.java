package dummy.jpa.interceptor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SafInterceptor extends EmptyInterceptor {

    private static final long serialVersionUID = 4013707250670795688L;

    private Set<Object> inserts = new HashSet<>();
    private Set<Object> updates = new HashSet<>();
    private Set<Object> deletes = new HashSet<>();

    @Override
    public void postFlush(Iterator entities) {
	String mtd =  new Object(){}.getClass().getEnclosingMethod().getName();

	ObjectMapper mapper = new ObjectMapper();

	entities.forEachRemaining(e->{
	    try {
		System.err.println(mtd + " : " + mapper.writeValueAsString(e));
	    } catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	});

	//	inserts.forEach(
	//		o-> {
	//		    try {
	//			System.err.println(mtd + " I : " + mapper.writeValueAsString(o));
	//		    } catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		    }
	//		}
	//		);
	//
	//
	//	updates.forEach(
	//		o-> {
	//		    try {
	//			System.err.println(mtd + " U : " + mapper.writeValueAsString(o));
	//		    } catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		    }
	//		}
	//		);
	//
	//	deletes.forEach(
	//		o-> {
	//		    try {
	//			System.err.println(mtd + " D : " + mapper.writeValueAsString(o));
	//		    } catch (Exception e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		    }
	//		}
	//		);

	//	inserts.clear();
	//	updates.clear();
	//	deletes.clear();

	super.postFlush(entities);
    }



    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	System.err.println( new Object(){}.getClass().getEnclosingMethod().getName());

	//	inserts.add(entity);

	return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	System.err.println( new Object(){}.getClass().getEnclosingMethod().getName());

	//	deletes.add(entity);

	super.onDelete(entity, id, state, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
	    String[] propertyNames, Type[] types) {
	System.err.println( new Object(){}.getClass().getEnclosingMethod().getName());

	//	updates.add(entity);

	return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }

}
