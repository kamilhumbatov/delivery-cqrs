package com.delivery.deliver.entires.repositories;

import com.delivery.deliver.entires.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
