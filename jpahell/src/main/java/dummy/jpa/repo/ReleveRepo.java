package dummy.jpa.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dummy.jpa.model.Releve;

@Repository
@Transactional
public interface ReleveRepo extends CrudRepository<Releve, Long> {

    
    List<Releve> findByType(String type);
    
}
