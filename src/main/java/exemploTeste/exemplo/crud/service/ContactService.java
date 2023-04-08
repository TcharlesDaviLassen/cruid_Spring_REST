package exemploTeste.exemplo.crud.service;

import java.util.ArrayList;
import java.util.List;

import exemploTeste.exemplo.crud.entity.Contact;
import exemploTeste.exemplo.crud.exception.BadResourceException;
import exemploTeste.exemplo.crud.exception.ResourceAlreadyExistsException;
import exemploTeste.exemplo.crud.repository.ContactRepositoryDAO;
import exemploTeste.exemplo.crud.specification.ContactSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContactService {

    @Autowired
    private ContactRepositoryDAO contactRepository;

    private boolean existsById(Long id) {
        return contactRepository.existsById(id);
    }

    public Contact findById(Long id) throws ResourceNotFoundException {
        Contact contact = (Contact) contactRepository.findById(id).orElse(null);
        if (contact == null) {
            throw new ResourceNotFoundException("Cannot find Contact with id: " + id);
        } else return contact;
    }

    public List<Contact> findAll(int pageNumber, int rowPerPage) {
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll(PageRequest.of(pageNumber - 1, rowPerPage)).forEach(contacts::add);
        return contacts;
    }

    public List<Contact> findAllByName(String name, int pageNumber, int rowPerPage) {
        Contact filter = new Contact();
        filter.setName(name);
        Specification<Contact> spec = new ContactSpecification(filter);

        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll(spec, PageRequest.of(pageNumber - 1, rowPerPage)).forEach(contacts::add);
        return contacts;
    }

    public Contact save(Contact contact) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(contact.getName())) {
            if (contact.getId() != null && existsById(contact.getId())) {
                throw new ResourceAlreadyExistsException("Contact with id: " +
                        contact.getId() +
                        " already exists"
                );
            }
            return contactRepository.save(contact);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save contact");
            exc.addErrorMessage("Contact is null or empty");
            throw exc;
        }
    }

    public void update(Contact contact) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(contact.getName())) {
            if (!existsById(contact.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + contact.getId());
            }
            contactRepository.save(contact);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save contact");
            exc.addErrorMessage("Contact is null or empty");
            throw exc;
        }
    }

    public void updateAddress(Long id, Contact address) throws ResourceNotFoundException {
        Contact contact = findById(id);
        contact.setAddress1(address.getAddress1());
        contact.setAddress2(address.getAddress2());
        contact.setAddress3(address.getAddress3());
        contact.setPostalCode(address.getPostalCode());

        contactRepository.save(contact);
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find contact with id: " + id);
        } else {
            contactRepository.deleteById(id);
        }
    }

    public Long count() {
        return contactRepository.count();
    }
}

/*
    @Service é o estereótipo para a camada de serviço.

    @Autowired A anotação permite que o Spring resolva e injete beans de colaboração em seu bean.
*/