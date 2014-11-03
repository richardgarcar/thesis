package cz.muni.fi.repository;

import cz.muni.fi.model.Node2Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xgarcar
 */
@RepositoryRestResource
public interface Node2NodeRepository extends CrudRepository<Node2Node, Long> {
}
