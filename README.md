# Desafio Cadastro de Pets

Sistema de cadastro de pets feito em Java, executado por linha de comando (CLI). Os dados agora são persistidos em um banco **SQL Server** usando **JDBC**.

## Tecnologias

- Java
- JDBC
- SQL Server Express
- Driver `mssql-jdbc`

## Como rodar

1. **Crie o banco:** execute o arquivo `banco.sql` no SQL Server (via SSMS ou `sqlcmd`).
2. **Adicione o driver:** baixe o `mssql-jdbc` e adicione ao classpath do projeto, ou coloque o `.jar` na pasta `lib/`.
3. **Defina a variável de ambiente `DB_SENHA`** com a senha do seu usuário do banco.

   No Windows (PowerShell):
   ```powershell
   setx DB_SENHA "sua_senha_aqui"
   ```
   Depois, reinicie o terminal/IDE para a variável ser reconhecida.

4. **Execute o projeto** pela sua IDE ou pelo terminal.

## Estrutura

- `PetDAO.java`: acesso aos dados dos pets
- `ConexaoBD.java`: conexão com o banco
- `banco.sql`: script de criação do banco e da tabela

## Considerações Finais 📝

- **Siga implementando o projeto e fazendo commits à medida que for concluindo as funcionalidades, seguindo o padrão sempre de [Conventional commits](https://www.youtube.com/watch?v=sbK9h45Jc5o)**

- **Crie um arquivo README.md com uma descrição do seu projeto e instruções de como executá-lo.**

### Gostou do projeto?
Se você chegou até aqui, parabéns! Você concluiu o primeiro grande desafio do guia. 🚀
