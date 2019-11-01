package br.com.telalogin.database;

public class Script {


    //Criando a tabela de clientes
    public static String getCriarTableCliente(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS CLIENTE ( ");
        sql.append("   CODIGO          INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("   NOME            VARCHAR (250) NOT NULL DEFAULT (''), ");
        sql.append("   ENDERECO        VARCHAR (255) NOT NULL DEFAULT (''), ");
        sql.append("   EMAIL           VARCHAR (200) NOT NULL DEFAULT (''), ");
        sql.append("   TELEFONE        VARCHAR (20)  NOT NULL DEFAULT (''), ");
        sql.append("   CPF             VARCHAR (10)  NOT NULL DEFAULT (''), ");
        sql.append("   DTNASCIMENTO    VARCHAR (20)  NOT NULL DEFAULT (''), ");
        sql.append("   CDLEITOR        VARCHAR (100) NOT NULL DEFAULT (''))");

        return sql.toString();
    }

    //Criando a Tabela de Leitores
    public static String getCriarTabelaCategoriaLeitor(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS LEITOR ( ");
        sql.append("   CODIGO           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("   CDCATEGORIA      VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   DSCATEGORIA      VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   NUMMAXDIAS       VARCHAR (10) NOT NULL DEFAULT (''))");

        return sql.toString();

    }

    //Criando a Tabela Categoria de Livro
    public static String getCriarTabelaCategoriaLivro(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS CATEGORIALIVRO ( ");
        sql.append("   CODIGO           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("   CDCATEGORIA      VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   DSCATEGORIA      VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   NUMMAXDIAS       VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   TXMULTA          VARCHAR (10) NOT NULL DEFAULT (''))");

        return sql.toString();
    }

    //Criando a Tabela Livro
    public static String getCriarTabelaLivro(){
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS LIVRO ( ");
        sql.append("   CODIGO           INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("   CODIGOLIVRO      VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   ISBN             VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   TITULO           VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   CATEGORIA        VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   AUTOR            VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   PALAVRACHAVE     VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   DTPUBLICACAO     VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   NUMEDICAO        VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   EDITORA          VARCHAR (70) NOT NULL DEFAULT (''), ");
        sql.append("   NUMPAGINA        VARCHAR (70) NOT NULL DEFAULT (''))");

        return sql.toString();
    }
}
