# ms-plataform-assign-group
Grupo de microserviços de gestão de assinaturas através de mensageria

# Arquitetura Escolhida:
   - Foi escolhida a arquitetura de microserviços com api's restfull
   - Foi respeitado o princípio SOLID de Single-Responsability
   - Foi priorizado a utilização de Clean Code
   - Outro princípio SOLID bem utilizado foi o princípio de Aberto/Fechado(OCP)
   - No relacionamento dos conteiners, foram priorizados suas dependencias de orquestração

## API de Publisher
   1. A API de publisher faz o envio da mensagem para fila
   2. Sua documentação fica exposta na url: `http://localhost:8081/swagger-ui`
   3. O tipo de requisição deve ser um POST
   4. A URL do endpoint é : `http://localhost:8081/api/v1/publisher`
   5. Tipos de retornos do endpoint: 
   - [StatusCode:400] Caso não seja enviado nenhum parâmetro, será retornado um erro 400, informando que houve validação de restrições
   - [StatusCode:500] Caso não seja possível realizar a comunicação com o RabbitMQ será retornado um erro interno do servidor
   - [StatusCode:201] É Retornado o status 201 quando a mensagem for publicada com sucesso na fila

## API de Consumer

1. A API de consumer que  faz a leitura das mensagens enviadas para fila através do ms de publisher
   2. Sua documentação fica exposta na url: `http://localhost:8082/swagger-ui`
   3. O tipo de requisição deve ser um POST
   4. A URL do endpoint é : `http://localhost:8082/api/v1/publisher`
   5. Tipos de retornos do endpoint: 
   - [StatusCode:400] Caso não seja enviado nenhum parâmetro, será retornado um erro 400, informando que houve validação de restrições
   - [StatusCode:500] Caso não seja possível realizar a comunicação com o RabbitMQ será retornado um erro interno do servidor
   - [StatusCode:201] É Retornado o status 201 quando a mensagem for publicada com sucesso na fila

## API de Report Subscriptions 

   1. A API de reporter fornece os dados dos subscriptions recebidos pela fila e armazenados no banco de dados
   2. Sua documentação fica exposta na url: `http://localhost:8083/swagger-ui`
   3. Os tipos de Requisições disponíveis são: GetByIdSubscripTion e GetAllEventHistory
   4. A URL do endpoint GetByIdSubscripTion é : `http://localhost:8083/api/v1/subscriptions/{subscriptionId}`
   4. A URL do endpoint GetAllEventHistory é : `http://localhost:8083/api/v1/eventHistory/`
   5. Tipos de retornos do endpoint: 
   - [StatusCode:200] Foram encontrados registros para os métodos GetByIdSubscripTion/GetAllEventHistory
   - [StatusCode:500] Será retornado caso exista um erro interno do servidor   
   - [StatusCode:404] É Retornado o status 404 quando não forem encontrados registros para os métodos GetByIdSubscripTion/GetAllEventHistory

## Serviço do RabbitMQ
   1. Importante estar com o conteiner em execução
   2. Seu acesso fica exposto na url para acessar externamente: `http://localhost:15672/`
   3. Seu acesso fica exposto na url para acessar internamente no conteiner: `http://localhost:5672/`
   4. Usuário e a senha de acesso: `guest`

## Acesso ao banco de dados Postgres
   1. Importante estar com o conteiner em execução
   2. É necessário possuir o DBEAVER ou similar para acesso
   3. O acesso ao banco fica exposto na url para acessar externamente: `http://localhost:5432/`
   4. Dados de acesso ao banco:
      - usuário: `assign`
      - senha: `assign`
      - schema: `public`
      - database: `postgres`
      - host: `localhost`
      - porta: `5432`

# Instruções de Execução

1. Clone o projeto: 
 ```git clone https://github.com/nfers/ms-plataform-assign-group.git```

2. Acesse o repositório: 

```cd ms-plataform-assign-group```

4. Execute o conteiner:

```docker-compose -f ./docker/docker-compose.yaml up --build -d```

5. Para chamar a API de envio de mensagem pra fila:

 ``` docker exec -it assign-publisher-notification make publish ```

- [Obs. o comando acima deve ser executado na raiz do repositório, pois o mesmo executará a subida de todos os serviços utilizados exemplificados no arquivo 'NOTIFICACOES.TXT']



```Desenvolvido por ncy.fers@gmail.com```
