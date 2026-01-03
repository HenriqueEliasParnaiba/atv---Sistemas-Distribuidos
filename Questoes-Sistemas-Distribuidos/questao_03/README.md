# Questão 03 – Garantia de entrega

Análise de possiveis problemas da Questão 02 

## Servidor cai durante o processamento de uma mensagem

Problema:
- O servidor pode ter recebido a mensagem, iniciado o processamento,
  mas cair antes de enviar o ACK ao cliente.

Soluções:
- Utilizar fila persistente (RabbitMQ, Kafka etc.) entre cliente e servidor.
- Registrar mensagens com estado (RECEBIDA, PROCESSADA) em armazenamento
  durável para permitir retomada.
- Tornar o processamento idempotente (repetir a mesma mensagem não gera
  efeitos duplicados).

## Cliente cai antes de receber a resposta

Problema:
- O servidor processa e envia o ACK, mas o cliente cai antes de ler.
- O cliente não sabe se a mensagem foi realmente concluída.

Soluções:
- Cliente manter lista local (ou em banco) de mensagens pendentes de ACK.
- Ao reiniciar, reenviar as mensagens marcadas como pendentes.
- Servidor deve ser idempotente, aceitando reprocessar mensagens com o mesmo ID.

## Push notification em vez de Pulling

- No *pulling* o cliente pergunta periodicamente: “tem algo novo?”.
  - Simples de implementar, mas gera tráfego extra e aumenta latência.
- No *push* (ex.: gRPC streaming, WebSocket), o servidor envia dados assim que
  houver evento.
  - Melhor para cenários em tempo real, mas exige canal aberto,
    reconexão automática e controle de fluxo.

Implicações:
- Necessidade de estratégias de *retry* com backoff exponencial.
- Uso de Dead Letter Queue (DLQ) para mensagens que falham várias vezes.
- Logs de auditoria para rastrear mensagens entregues ou perdidas.
