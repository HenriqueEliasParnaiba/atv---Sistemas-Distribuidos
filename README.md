# atv---Sistemas-Distribuidos
Comunicação Indireta, Assincronicidade e Garantia de Entrega de Mensagens


###  Questão 1 – Arquitetura baseada em eventos (HTTP + Docker)

####  Subir os serviços

```bash
cd questao_01
docker compose up --build


 Encerrar a execução

Quando terminar os testes, pressione:

CTRL + C
e depois execute:

docker compose down


Isso irá:

✔ parar os containers
✔ liberar a rede gerada pelo compose


 Questão 2 – gRPC Assíncrono (Streaming Bidirecional)

A Questão 2 é composta por três módulos Maven independentes:

grpc-share   → módulo compartilhado com o .proto e DTOs
grpc-server  → servidor gRPC
grpc-client  → cliente gRPC


A execução deve seguir esta ordem obrigatória: 

Compilar o módulo compartilhado (grpc-share)

Abra o terminal na pasta do projeto e execute:

cd questao_02/grpc-share

mvn clean install


Isso gera e instala as classes Java criadas a partir do arquivo .proto,
para que os outros módulos possam usá-las.

Se aparecer:

BUILD SUCCESS


significa que está tudo certo 

Iniciar o Servidor gRPC (grpc-server)

Agora execute:

cd ../grpc-server
mvn clean package
mvn exec:java


Isso irá:

compilar o servidor

iniciar o serviço gRPC na porta 50051

ficar aguardando conexões do cliente

Você deverá ver algo como:

[grpc-server] Servidor ativo na porta 50051


 Deixe esse terminal aberto — o servidor deve continuar rodando.

Executar o Cliente gRPC (grpc-client)

Abra um novo terminal e rode:

cd questao_02/grpc-client
mvn clean package
mvn exec:java
