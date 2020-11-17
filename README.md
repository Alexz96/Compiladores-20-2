**Compiladores:**
**Trabalho - Analisador Sintático**

O objetivo deste trabalho foi programar um Analisador Léxico para um subconjunto da linguagem LPD, a qual é baseada em Pascal. Dentre alguns dos seus requisitos estão a identificação de uma estrutura de programa principal e declaração de identificadores, como por exemplo, o nome do programa, o nome de variáveis.

Requisitos para execução do trabalho:
- IDE que suporte a Linguagem de Programação Java. Exemplo de algumas delas: NetBeans, Eclipse, IntelliJ.
- JDK mínimo: 1.8

Instruções para obter o Algoritmo:
- Realizar o download do arquivo do projeto que foi entregue na atividade.
- Caso desejar fazer o download do código através do diretório, então antes de abrir o arquivo na IDE, deverá ser acessar o link "https://github.com/Alexz96/Compiladores-20-2/". No repositório "Compiladores-20-2", baixar o projeto como arquivo .zip e acessar o caminho do diretório onde foi realizado o download e descompactar o arquivo zip. 

Instruções para execução do projeto dentro da IDE:
- Importar o projeto que foi realizado o download.
- Conferir se os pacotes foram criados corretamente e caso necessário, ajuste o nome e estrutura dos mesmos antes de executar.
- Abra o arquivo que representa a classe LexicoMain e faça a execução do mesmo, de acordo com o comando utilizado na respectiva IDE.
- Será realizada a execução do código com base no arquivo "teste1.lpd" que está disponível na estrutura dos pacotes e se desejar realizar alguma alteração ou adição para testes, basta abrir o mesmo, fazer a edição e salvar o arquivo. Após isso, basta compilar o projeto novamente.

De modo geral, o Analisador Léximo é capaz de reconhecer os tokens do programa, inseri-los em uma lista de tokens (tabela de símbolos simplificada) com cada registro contendo sei respetivo tipo, lexema, linha e coluna de início do token. Através dele, é possível receber o nome de um arquivo texto com o código fonte e fazer a leitura e apresentação da lista de tokens ao final.
