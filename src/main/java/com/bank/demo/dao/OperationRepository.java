package com.bank.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.demo.entities.Operation;

/**
 * Description of the file Operation Repository.java <br>
 * 
 *
 *         This interface is an extension of JpaRepository.java <br>
 *         It is used to access the Operation table at the data base level
 */
public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select op from Operation op where op.account.accountId = :x order by op.operationDate desc")
	public Page<Operation> getAccountOperationsByPage(@Param("x") int accountId, Pageable pageable);

}
