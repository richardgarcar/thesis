package cz.muni.fi.repository;

import cz.muni.fi.model.Node;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xgarcar
 */
@RepositoryRestResource
public interface NodeRepository extends PagingAndSortingRepository<Node, Long> {
}
