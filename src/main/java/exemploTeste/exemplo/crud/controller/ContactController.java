package exemploTeste.exemplo.crud.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;

import exemploTeste.exemplo.crud.entity.Contact;
import exemploTeste.exemplo.crud.exception.BadResourceException;
import exemploTeste.exemplo.crud.exception.ResourceAlreadyExistsException;
import exemploTeste.exemplo.crud.service.ContactService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Contact>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(required = false) String name) {
        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.ok(contactService.findAll(pageNumber, ROW_PER_PAGE));
        } else {
            return ResponseEntity.ok(contactService.findAllByName(name, pageNumber, ROW_PER_PAGE));
        }
    }

    @GetMapping(value = "/contacts/{contactId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Contact> findContactById(@PathVariable long contactId) {
        JSONObject obj = new JSONObject();
        try {
            Contact book = contactService.findById(contactId);
            return ResponseEntity.ok(book);  // return 200, with json body
        } catch (ResourceNotFoundException ex) {
            return null;
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // return 404, with null body
        }
    }

    @PostMapping(value = "/contacts")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact)
            throws URISyntaxException {
        try {
            Contact newContact = contactService.save(contact);
            return ResponseEntity.created(new URI("/api/contacts/" + newContact.getId()))
                    .body(contact);
        } catch (ResourceAlreadyExistsException ex) {
            // log exception first, then return Conflict (409)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (BadResourceException ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/contacts/{contactId}")
    public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact,
                                                 @PathVariable long contactId) {
        try {
            contact.setId(contactId);
            contactService.update(contact);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            // log exception first, then return Not Found (404)
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (BadResourceException ex) {
            // log exception first, then return Bad Request (400)
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("/contacts/{contactId}")
    public ResponseEntity<Void> updateAddress(@PathVariable long contactId,
                                              @RequestBody Contact address) {
        try {
            contactService.updateAddress(contactId, address);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            // log exception first, then return Not Found (404)
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/contacts/{contactId}")
    public ResponseEntity<Void> deleteContactById(@PathVariable long contactId) {
        try {
            contactService.deleteById(contactId);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}


/*
    @RestControlleré uma anotação de conveniência que é anotada com @Controllere @ResponseBody.
    Essa anotação é aplicada a uma classe para marcá-la como um manipulador de solicitações e usada para serviços da Web RESTful usando Spring MVC.

    @RequestMappingA anotação mapeia solicitações HTTP para métodos manipuladores de controladores. Essa é uma das anotações mais comuns usadas em aplicativos da Web Spring. Existem @GetMapping, @PostMapping, @PutMapping,@PatchMappinge @DeleteMappingpara lidar com diferentes tipos de solicitação HTTP.

    @ValidA anotação é para garantir que o corpo da solicitação seja válido.
*/