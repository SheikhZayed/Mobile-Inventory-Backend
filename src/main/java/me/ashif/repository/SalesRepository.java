package me.ashif.repository;

import me.ashif.model.SalesModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by techjini on 9/1/17.
 */
@Repository
public interface SalesRepository extends CrudRepository<SalesModel,Long>{
}
