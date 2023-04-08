## CONTROLER

## @RestController
É uma anotação de conveniência que é anotada com @Controller e @ResponseBody.

Essa anotação é aplicada a uma classe para marcá-la como um manipulador de solicitações e usada para serviços da Web RESTful usando Spring MVC.

## @RequestMapping
A anotação mapeia solicitações HTTP para métodos manipuladores de controladores. Essa é uma das anotações mais comuns usadas em aplicativos da Web Spring. Existem @GetMapping, @PostMapping, @PutMapping,@PatchMappinge @DeleteMappingpara lidar com diferentes tipos de solicitação HTTP.

## @Valid 
A anotação é para garantir que o corpo da solicitação seja válido.

##

## ENTIDADE

## @Entity 
A anotação define que uma classe pode ser mapeada para uma tabela. É obrigatório, ao criar uma nova entidade você tem que fazer pelo menos duas coisas:
anotou com @Entity
crie um campo id e anote-o com @Id

## @Table 
A anotação permite especificar os detalhes da tabela que será usada para persistir a entidade no banco de dados.

## @Cache 
Anotação usada para fornecer configuração de cache. A estratégia READ_WRITE é um mecanismo de simultaneidade de cache assíncrono e para evitar problemas de integridade de dados (por exemplo, entradas de cache obsoletas), ele usa um mecanismo de bloqueio que fornece garantias de isolamento de unidade de trabalho.

## @Getter e @Setter 
Permite que o lombok gere o getter/setter padrão automaticamente.

## @Id 
Anotação marca um campo como uma chave primária.

## @GeneratedValue 
A anotação especifica que um valor será gerado automaticamente para esse campo. Geralmente é usado para estratégia de geração de chave primária, como exemplo, estamos usando IDENTITYa estratégia que significa "incremento automático".

## @NotBlank
Parte do Hibernate Validator; uma String restrita é válida se não for nula e o comprimento aparado for maior que zero

## @Column 
É usado para especificar a coluna mapeada para uma propriedade ou campo persistente.

## REPOSITORIO


## ContactRepository 
Estende **PagingAndSortingRepository** , uma interface que fornece operações CRUD genéricas e
adiciona métodos para recuperar entidades usando a abstração de paginação e classificação.

ContactRepository também estende a **interface JpaSpecificationExecutor<T>** .
Essa interface fornece métodos que podem ser usados para chamar consultas de banco de dados usando a API JPA Criteria.
T descreve o tipo da entidade consultada, no nosso caso é Contact. Para especificar
as condições da consulta de banco de dados invocada,
precisamos criar uma nova implementação de Specification<T> : classe ContactSpecification.

## SERVICE

## @Service 
É o estereótipo para a camada de serviço.

## @Autowired 
A anotação permite que o Spring resolva e injete beans de colaboração em seu bean.

## TESTE

## @SpringBootTest
Fornece uma maneira conveniente de iniciar um contexto de aplicativo a ser usado em um teste.


Neste tutorial rápido, discutiremos o uso da anotação @RunWith no framework JUnit 5.
No JUnit 5, a anotação @RunWith foi substituída pela anotação mais poderosa @ExtendWith .
No entanto, a anotação @RunWith ainda pode ser usada no JUnit 5 para fins de compatibilidade com versões anteriores.

## @AutoConfigureWebTestClient
Adiciona WebTestClientao contexto do aplicativo de teste. Ele nos permite testar os endpoints do servidor.

## @AutoConfigureTestDatabase
Isso nos permite executar o teste em um banco de dados real em vez do incorporado.

## @RestClientTest
É útil quando queremos testar nossos RestTemplates. Ele configura automaticamente os componentes necessários, além de um MockRestServiceServerobjeto que nos ajuda a simular respostas para as solicitações provenientes das RestTemplatechamadas.

## @JsonTest
autoconfigura mapeadores JSON e classes como JacksonTesterou GsonTester. Com eles, podemos verificar se nossa serialização/desserialização JSON está funcionando corretamente ou não.

## @AutoConfigureMockMvcpara 
Adicionar uma MockMvcinstância ao contexto do aplicativo.

Usamos esse MockMvc objeto para realizar uma POST solicitação ao nosso aplicativo e verificar se ele responde conforme o esperado.

Em seguida, usamos o UserRepositorycontexto do aplicativo para verificar se a solicitação levou a uma alteração esperada no estado do banco de dados.

