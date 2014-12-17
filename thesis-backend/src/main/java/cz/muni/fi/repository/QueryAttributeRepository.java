package cz.muni.fi.repository;

import cz.muni.fi.model.QueryAttribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author xgarcar
 */
@RepositoryRestResource
public interface QueryAttributeRepository extends CrudRepository<QueryAttribute, Long> {
}
