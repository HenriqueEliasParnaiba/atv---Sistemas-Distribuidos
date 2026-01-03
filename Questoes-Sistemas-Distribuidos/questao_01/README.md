# Questão 01 — Arquitetura baseada em eventos (HTTP)

Fluxo: clientapp → sender → receiver → serverapp

Cada nó é um projeto Java/Maven independente e é empacotado em um container via Dockerfile.
O Docker Compose orquestra os nós para compor o sistema completo.
