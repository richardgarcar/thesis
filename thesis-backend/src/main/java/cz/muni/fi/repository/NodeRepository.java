package cz.muni.fi.repository;

import cz.muni.fi.model.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xgarcar
 */
@RepositoryRestResource()
public interface NodeRepository extends CrudRepository<Node, Long> {
}
