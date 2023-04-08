package exemploTeste.exemplo.crud.repository;

import exemploTeste.exemplo.crud.entity.Contact;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ContactRepositoryDAO extends PagingAndSortingRepository<Contact, Long>,
        JpaSpecificationExecutor<Contact> {
    
    Contact save(Contact contact);

    Optional<Object> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    Long count();
}

/*
   ContactRepository estende PagingAndSortingRepository , uma interface que fornece operações CRUD genéricas e
   adiciona métodos para recuperar entidades usando a abstração de paginação e classificação.

   ContactRepository também estende a interface JpaSpecificationExecutor<T> .
   Essa interface fornece métodos que podem ser usados para chamar consultas de banco de dados usando a API JPA Criteria.
   T descreve o tipo da entidade consultada, no nosso caso é Contact. Para especificar
   as condições da consulta de banco de dados invocada,
   precisamos criar uma nova implementação de Specification<T> : classe ContactSpecification.
*/
