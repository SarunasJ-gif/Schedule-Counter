package com.sarunas.Scheduler.Counter.repository;

import com.sarunas.Scheduler.Counter.model.WorkingTimeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkingTimeRepository extends CrudRepository<WorkingTimeEntity, Long> {
}
