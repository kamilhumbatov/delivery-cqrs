package com.delivery.courier.entires.repositories;

import com.delivery.courier.entires.AccountQueryEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountQueryEntity, String> {
}
