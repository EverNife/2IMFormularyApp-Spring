# 2IM BackEnd Project

O front end desse projeto pode ser encontrado em: https://github.com/EverNife/2IMFormularyApp-Nextjs

## Como executar:

Esse projeto requer java 17+ devido a utilização da ultima versão do SpringBoot.

Para buildar o projeto use:

```
.gradlew build
```

## SQL

A criação do banco de dados SQL é dinamica e feita diretamente pelo Hibernate, ou seja, nao é necessário a existencia da definição de um arquivo '.sql'

Para facilitar o processo de teste, por padrão a aplicação utiliza do banco de dados H2 que é um banco de dados SQL em memória!
