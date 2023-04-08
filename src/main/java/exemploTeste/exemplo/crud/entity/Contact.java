package exemploTeste.exemplo.crud.entity;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String phone;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String postalCode;

    @Column(length = 4000)
    private String note;
}


/*
    @Entity fA anotação define que uma classe pode ser mapeada para uma tabela. É obrigatório, ao criar uma nova entidade você tem que fazer pelo menos duas coisas:

    anotou com @Entity
    crie um campo id e anote-o com @Id

    @Table A anotação permite especificar os detalhes da tabela que será usada para persistir a entidade no banco de dados.

    @Cacheanotação usada para fornecer configuração de cache. A estratégia READ_WRITE é um mecanismo de simultaneidade de cache assíncrono e para evitar problemas de integridade de dados (por exemplo, entradas de cache obsoletas), ele usa um mecanismo de bloqueio que fornece garantias de isolamento de unidade de trabalho.

    @Gettere @Setterpermite que o lombok gere o getter/setter padrão automaticamente.

    @Idanotação marca um campo como uma chave primária.

    @GeneratedValueA anotação especifica que um valor será gerado automaticamente para esse campo. Geralmente é usado para estratégia de geração de chave primária, como exemplo, estamos usando IDENTITYa estratégia que significa "incremento automático".

    @NotBlankparte do Hibernate Validator; uma String restrita é válida se não for nula e o comprimento aparado for maior que zero

    @Column é usado para especificar a coluna mapeada para uma propriedade ou campo persistente.
*/