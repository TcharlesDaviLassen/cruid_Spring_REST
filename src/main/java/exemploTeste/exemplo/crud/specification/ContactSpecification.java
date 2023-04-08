package exemploTeste.exemplo.crud.specification;

import exemploTeste.exemplo.crud.entity.Contact;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.jetbrains.annotations.NotNull;
import org.junit.Ignore;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification implements Specification<Contact> {

    private Contact filter;

    public ContactSpecification(Contact filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Specification<Contact> and(Specification<Contact> other) {
        return Specification.super.and(other);
    }

    @Override
    public Specification<Contact> or(Specification<Contact> other) {
        return Specification.super.or(other);
    }

//    @Override
//    public jakarta.persistence.criteria.Predicate toPredicate(jakarta.persistence.criteria.Root<T> root, jakarta.persistence.criteria.CriteriaQuery<?> query, jakarta.persistence.criteria.CriteriaBuilder criteriaBuilder) {
//        return null;
//    }

    public Predicate toPredicate(@NotNull Root<Contact> root, @NotNull CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {

        Predicate p = cb.disjunction();

        if (filter.getName() != null) {
            p.getExpressions().add(cb.like(root.get("name"), "%" + filter.getName() + "%"));
        }

        if (filter.getPhone() != null) {
            p.getExpressions().add(cb.like(root.get("phone"), "%" + filter.getPhone() + "%"));
        }

        return p;
    }
}