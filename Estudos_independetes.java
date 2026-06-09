
public class Estudos_independetes {
  //Função salvar no arquivo Produtos.CSV
  public static void saveProd(String linha){
    Arquivo produtos = new Arquivo("Produtos.csv");
    produtos.abrirEscrita(true);
    produtos.escreverLinha(linha);
    produtos.fecharArquivo();
  }
  //Função salvar no arquivo Pedidos.CSV
    public static void savePed(String linhaPedido){
    Arquivo pedidos = new Arquivo("Pedidos.CSV");
    pedidos.abrirEscrita(true);
    pedidos.escreverLinha(linhaPedido);
    pedidos.fecharArquivo();

  }
  //Função relatório dos pedidos
  public static void relPed(Arquivo pedidos){
  int rPed, Poss1, Poss2= 0;
  String linha = "";
  do {
    rPed = Entrada.leiaInt(menuJanela("RELATÓRIO PEDIDOS") + "Escolha a opção que você quer acessar\n1- Todos os pedidos \n2- Ped. P/Clientes \n3- Ped. P/Produto \n4- Voltar");
    switch(rPed){
      //Todos os pedidos
      case (1):
                pedidos.abrirLeitura();
                linha = pedidos.lerLinha();
                double valorTot = 0;
                String dadosTODOS[] = new String[4];
                while (linha != null) {
                  Poss1 = 0;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosTODOS[0] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosTODOS[1] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosTODOS[2] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosTODOS[3] = linha.substring(Poss1);

                    System.out.println(menu("TODOS OS PEDIDOS"));
                    System.out.println("CPF: " + dadosTODOS[0]);
                    System.out.println("Cod. Produto: " + dadosTODOS[1]);
                    System.out.println("Quantidade: " + dadosTODOS[2]);
                    System.out.println("Valor Total: " + dadosTODOS[3]);
                    System.out.println("====================================================== ");
                    double valConvert = Double.parseDouble(dadosTODOS[3]);
                    valorTot = valConvert + valorTot;
                linha = pedidos.lerLinha();
                }
                System.out.println("Valor total: " + valorTot);
                System.out.println("====================================================== ");
                System.out.println("");
                System.out.println("");
                pedidos.fecharArquivo();
      break;
      //pedidos por cliente
      case (2):
        String nomeBusca = Entrada.leiaString(menuJanela("CONSULTA POR CLIENTE")+"Digite o CPF:");

                pedidos.abrirLeitura();
                linha = pedidos.lerLinha();
                double valorTotCli= 0;
                boolean achouCPF = false;
                String dadosCPF[] = new String[4];

                while (linha != null) {
                  //String dados[] = linha.split(";");
                  Poss1 = 0;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCPF[0] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCPF[1] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCPF[2] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosCPF[3] = linha.substring(Poss1);

                  if (dadosCPF[0].toLowerCase().contains(nomeBusca.toLowerCase())) {
                    System.out.println(menu("PEDIDO POR CLIENTE"));
                    System.out.println("CPF: " + dadosCPF[0]);
                    System.out.println("Cod. Produto: " + dadosCPF[1]);
                    System.out.println("Quantidade: " + dadosCPF[2]);
                    System.out.println("Valor total: " + dadosCPF[3]);
                    System.out.println("====================================================== ");
                    double valConvert = Double.parseDouble(dadosCPF[3]);
                    valorTotCli = valConvert + valorTotCli;
                    achouCPF = true;
                  }
                  if (!achouCPF){
                  }

                  linha = pedidos.lerLinha();
                }
                System.out.println("Valor total: " + valorTotCli);
                System.out.println("====================================================== ");
                System.out.println("");
                System.out.println("");
                pedidos.fecharArquivo();
                if (!achouCPF){
                  System.out.println(menu("CPF NÃO ENCONTRADO"));
                  }

      break;
      case (3):
        //Pedidos por produto
        int CodProdRel = Entrada.leiaInt(menuJanela("CONSULTA POR PRODUTO") + "Digite Código do Produto");
        pedidos.abrirLeitura();
                linha = pedidos.lerLinha();
                double valorTotProd = 0;
                String dadosProd[] = new String[4];

                while (linha != null) {
                  Poss1 = 0;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosProd[0] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosProd[1] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosProd[2] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosProd[3] = linha.substring(Poss1);  

                  
                    if (Integer.parseInt(dadosProd[1]) == CodProdRel){
                    System.out.println(menu("PEDIDOS POR PRODUTO"));
                    System.out.println("CPF: " + dadosProd[0]);
                    System.out.println("Cod. Produto: " + dadosProd[1]);
                    System.out.println("Quantidade: " + dadosProd[2]);
                    System.out.println("Valor Total: " + dadosProd[3]);
                    System.out.println("====================================================== ");
                    double valConvert = Double.parseDouble(dadosProd[3]);
                    valorTotProd = valConvert + valorTotProd;
                linha = pedidos.lerLinha();
                }
                }
                System.out.println("Valor total: " + valorTotProd);
                System.out.println("====================================================== ");
                System.out.println("");
                System.out.println("");
                pedidos.fecharArquivo();
      break;
      case (4):
        System.out.println(menu("VOLTANDO"));
      break;
      default:
        System.out.println(menu("OPÇÃO INVÁLIDA"));
      break;
    }
  } while (rPed != 4);
  }
  //Menu p/Janela Entrada
  public static String menuJanela(String titulo){
String cabecalho = "<html><center>";
   cabecalho += "======================================================<br>";
   cabecalho += "<b>" + titulo +"<b><br>";
   cabecalho += "======================================================<br>";
   cabecalho += "<br>";

  return cabecalho;
}
//Menu p/Terminal para deixar mais agradavel a visulalização
   public static String menu(String titulo){
    int tamanhoLinha = 54;
    int espacosEsquerda = (tamanhoLinha - titulo.length()) /2;
    if(espacosEsquerda < 0){
    espacosEsquerda = 0;
    }

    String cabecalho = "======================================================\n";
    for (int j = 0; j < espacosEsquerda; j++) {
        cabecalho += " ";
    }
    cabecalho += titulo + "\n";
    cabecalho += "======================================================\n";
    cabecalho += "\n";

    return cabecalho;
}
//Função para trazer um relatório dos grupos existentes, para que o usuário não fique precisando adivinhar 
//quais estão cadastrados
  public static void relGrupos(Arquivo produtos){
    System.out.println(menu("\nRELATÓRIO DE GRUPOS CADASTRADOS"));

    //Vetor para não termos repetições de grupos
    String[] gruposUnicos = new String[100];
    int totalDeGrupos = 0;
    String dadosGrup[] = new String[5];
    int Poss1, Poss2 = 0;
    
    //Abertura do arquivo
    produtos.abrirLeitura();
    String linha = produtos.lerLinha();

    while (linha != null){
                  Poss1 = 0;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosGrup[0] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosGrup[1] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosGrup[2] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosGrup[3] = linha.substring(Poss1);
      String grupoAtual = dadosGrup[2];

      //Verificação se o grupo ja foi lido
      boolean jaExiste = false;
      for (int i = 0; i < totalDeGrupos; i++){
        if (gruposUnicos[i].equalsIgnoreCase(grupoAtual)){
          jaExiste = true;
          break;
      }
    }
    if (!jaExiste){
      gruposUnicos[totalDeGrupos] = grupoAtual;
      totalDeGrupos++;
    }
    linha = produtos.lerLinha();
  }
  produtos.fecharArquivo();

  //Mostra o relatório dosgrupos que estão cadastrados
  System.out.println("Totais de grupos: " + totalDeGrupos);
  for (int j = 0; j < totalDeGrupos; j++){
    System.out.println("Grupo " + (j+1) + ": " + gruposUnicos[j]);
  }
  System.out.println("-------------------------------------");
}
  public static void main(String[] args) {
    Arquivo produtos = new Arquivo("Produtos.csv");
    Arquivo pedidos = new Arquivo("Pedidos.csv");
    String linha, nome, /* cpf */grupo;
    int estoque = 0;
    int codProd = 1;
    double precoVend = 0;
    int i = 0;


    // Inicio do programa, vao perguntar a qual parte do sistema voce quer acessar.
    do{
    i = Entrada.leiaInt(menuJanela("INICIO SISTEMA") +
        "Digite a opcao que você quer acessar\n1 - Cadastro dos produtos\n2 - Pedidos.\n3 - Finalizar o programa.");
    switch (i) {
      // Cadastro de produtos.
      case (1):
        int pr = 0;
        do{
        pr = Entrada.leiaInt(
            menuJanela("PRODUTOS") + "\n1 - Cadastrar novo produto\n2 - Ajuste no cadastro\n3 - Consultar produto\n4 - Retornar ao menu principal.");
        switch (pr) {
          // Cadastrar novo produto.
          case (1):
            menu("CADASTRAR PRODUTO");
            // Parte do codigo para identificar qual o proximo dos produtos
            produtos.abrirLeitura();
            linha = produtos.lerLinha();
            while (linha != null) {
              codProd++;
              linha = produtos.lerLinha();
            }
            produtos.fecharArquivo();
            nome = Entrada.leiaString("Escreva o nome do novo Produto");
            grupo = Entrada.leiaString("Escreva de qual grupo o produto se encontra ");
            estoque = Entrada.leiaInt("Escreva a quantidade inicial do estoque");
            precoVend = Entrada.leiaDouble("Escreva qual o valor de venda do produto");
            linha = codProd + ";" + nome + ";" + grupo + ";" + estoque + ";" + precoVend;
            saveProd(linha);
            break;

          // Ajustes no cadastro.
          case (2):
            
            int pr_aj = 0;
            do{
            pr_aj = Entrada.leiaInt(menuJanela("AJUSTE CADASTRO") + "\n1 - Nome\n2 - Preco\n3 - Quantidade no estoque\n4 - Grupo\n5 - Voltar. ");
            switch (pr_aj) {
              // Editar o nome do produto.
              case (1):
                break;
              // Editar o preco do produto.
              case (2):
                break;
              // Editar estoque.
              case (3):
                break;
              // Trocar o grupo do produto
              case (4):
                break;
              // Votar a tela do cadastro do poduto.
              case (5):
                System.out.println("Voltando a tela inicial");
                break;
              // identificador se a opcao existe
              default:
                System.out.println("Opcao invalida");
                break;
            }
          }
          while (pr_aj != 5);
            
            break;

          // Consultar produto.
          case (3):
            int pr_con = 0;
            do {

            pr_con = Entrada
                .leiaInt(menuJanela("CONSULTA PRODUTOS") + "Escolha a forma que voce quer pesquisar\n1 - Codigo\n2 - nome\n3 - Grupo\n4 - Voltar");

            switch (pr_con)  {
              // Pesquisa por codigo ( pelo numero de produtos que for cadastrando).
              case (1):

                int codigo = Entrada.leiaInt(menuJanela("CONSULTA POR CÓDIGO")+"Digite o codigo do produto:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int Poss1, Poss2 =0;
                String dadosCodProd[] = new String[5];
                boolean encontrou = false;

                while (linha != null) {

                  Poss1 = 0;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCodProd[0] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCodProd[1] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCodProd[2] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCodProd[3] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosCodProd[4] = linha.substring(Poss1);

                  if (Integer.parseInt(dadosCodProd[0]) == codigo) {
                    System.out.println("Codigo: " + dadosCodProd[0]);
                    System.out.println("Nome: " + dadosCodProd[1]);
                    System.out.println("Grupo: " + dadosCodProd[2]);
                    System.out.println("Estoque: " + dadosCodProd[3]);
                    System.out.println("Preco: " + dadosCodProd[4]);

                    encontrou = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!encontrou) {       
                  System.out.println(menu("PRODUTO NÂO ENCONTRADO"));
                }
                

                break;


              // Pesquisa por nome.
              case (2):

                String nomeBusca = Entrada.leiaString(menuJanela("CONSULTA POR NOME")+"Digite o nome:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int PossN1, PossN2 =0;
                String dadosNomProd[] = new String[5];
                boolean achouNome = false;

                while (linha != null) {
                  PossN1 = 0;
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[0] = linha.substring(PossN1, PossN2);

                  Poss1 = PossN2 + 1;
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[1] = linha.substring(PossN1, PossN2);

                  PossN1 = PossN2 + 1;
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[2] = linha.substring(PossN1, PossN2);

                  PossN1 = PossN2 + 1;
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[3] = linha.substring(PossN1, PossN2);

                  PossN1 = PossN2 + 1;
                  dadosNomProd[4] = linha.substring(PossN1);

                  if (dadosNomProd[1].toLowerCase().contains(nomeBusca.toLowerCase())) {
                    System.out.println("Codigo: " + dadosNomProd[0]);
                    System.out.println("Nome: " + dadosNomProd[1]);
                    System.out.println("Grupo: " + dadosNomProd[2]);
                    System.out.println("Estoque: " + dadosNomProd[3]);
                    System.out.println("Preco: " + dadosNomProd[4]);

                    achouNome = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouNome) {
                  System.out.println(menu("NENHUM PRODUTO ENCONTRADO"));
                }

                break;

              // Pesquisa pelo grupo(Tres todos os produtos do grupo).
              case (3):
                relGrupos(produtos);

                String grupoBusca = Entrada.leiaString(menuJanela("CONSULTA POR GRUPO")+"Digite o grupo:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int PossG1, PossG2 = 0;
                String dadosGrupProd[] = new String[5];
                boolean achouGrupo = false;

                while (linha != null) {
                  PossG1 = 0;
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[0] = linha.substring(PossG1, PossG2);

                  PossG1 = PossG2 + 1;
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[1] = linha.substring(PossG1, PossG2);

                  PossG1 = PossG2 + 1;
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[2] = linha.substring(PossG1, PossG2);

                  PossG1 = PossG2 + 1;
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[3] = linha.substring(PossG1, PossG2);
                  
                  PossG1 = PossG2 + 1;
                  dadosGrupProd[4] = linha.substring(PossG1);

                  if (dadosGrupProd[2].equalsIgnoreCase(grupoBusca)) {
                    System.out.println("Codigo: " + dadosGrupProd[0]);
                    System.out.println("Nome: " + dadosGrupProd[1]);
                    System.out.println("Grupo: " + dadosGrupProd[2]);
                    System.out.println("Estoque: " + dadosGrupProd[3]);
                    System.out.println("Preco: " + dadosGrupProd[4]);

                    achouGrupo = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouGrupo) {
                  System.out.println(menu("NENHUM PRODUTO ENCONTRADO"));
                }

                break;

              // Finalizar a tela de consulta
              case (4):
                System.out.println(menu("VOLTANDO"));
                break;

              default:
                System.out.println(menu("OPÇÃO INVÁLIDA"));
                break;
            }

          }

            while (pr_con != 4);
              
            break;

          // Finalizar aba de produtos.
          case (4):
            System.out.println(menu("VOLTANDO A TELA INICIAL"));
            break;

          // Identidficador para ver se a op??o ? real
          default:
            System.out.println(menu("OPÇÃO INVÁLIDA"));
            break;
        }
      }

        // Esse while serve para repetir a pergunta dos produtos at? ser digitado 4.
       while (pr != 4);
          
        break;

      // Pedidos
      case (2):
        int Ped = 0;
        do{
          Ped = Entrada.leiaInt(menuJanela("PEDIDOS") + "Digite a opção que você quer acessar \n1- Cadastro de Pedidos \n2- Relatório Pedidos \n3- Voltar a tela inicial");
        switch(Ped){
        case (1):
        System.out.println(menu("CADASTRO PEDIDOS / VENDAS"));
        String cpfCliente = Entrada.leiaString("Digite o CPF do cliente:");

        int continuarPedido = 1; // Variável de controle do laço de itens

        // LAÇO PRINCIPAL DO PEDIDO: Permite incluir vários itens para o mesmo CPF
        while (continuarPedido == 1) {
          int codProdBusca = Entrada.leiaInt("Digite o código do produto:");
          int quantDesejada = Entrada.leiaInt("Digite a quantidade desejada:");

          // Abrimos o arquivo de produtos para verificar a existência e o estoque
          produtos.abrirLeitura();

          linha = produtos.lerLinha();
          boolean produtoExiste = false;
          boolean estoqueSuficiente = false;

          String nomeProduto = "";
          int estoqueAtual = 0;
          double precoUnitario = 0;

          // 1. PASSO: Buscar o produto e verificar o estoque
          while (linha != null) {
            int PossPed1, PossPed2 = 0;
            String[] dadosProd = new String[5];
                  PossPed1 = 0;
                  PossPed2 = linha.indexOf(';',PossPed1);
                  dadosProd[0] = linha.substring(PossPed1, PossPed2);

                  PossPed1 = PossPed2 + 1;
                  PossPed2 = linha.indexOf(';',PossPed1);
                  dadosProd[1] = linha.substring(PossPed1, PossPed2);

                  PossPed1 = PossPed2 + 1;
                  PossPed2 = linha.indexOf(';',PossPed1);
                  dadosProd[2] = linha.substring(PossPed1, PossPed2);
                  
                  PossPed1 = PossPed2 + 1;
                  dadosProd[3] = linha.substring(PossPed1);


            if (Integer.parseInt(dadosProd[0]) == codProdBusca) {
              produtoExiste = true;
              nomeProduto = dadosProd[1];
              estoqueAtual = Integer.parseInt(dadosProd[3]);
              precoUnitario = Double.parseDouble(dadosProd[4]);

              if (estoqueAtual >= quantDesejada) {
                estoqueSuficiente = true;
              }
              break; // Já achou o produto, pode parar o while de busca
            }
            linha = produtos.lerLinha();
          }
          produtos.fecharArquivo();

          // 2. PASSO: Processar o item atual se tudo estiver correto
          if (!produtoExiste) {
            // Exibe janela gráfica nativa informando que o produto não existe
            javax.swing.JOptionPane.showMessageDialog(null,
                "Erro: Produto não encontrado!",
                "Aviso do Sistema",
                javax.swing.JOptionPane.ERROR_MESSAGE);

          } else if (!estoqueSuficiente) {
            // CORREÇÃO AQUI: Exibe a janela gráfica oficial do Java avisando a falta de
            // estoque
            String textoAviso = "AVISO DE ESTOQUE INSUFICIENTE!\n\n" +
                "Produto: " + nomeProduto + "\n" +
                "Estoque Atual: " + estoqueAtual + " unidades.\n" +
                "Quantidade Solicitada: " + quantDesejada + " unidades.";

            javax.swing.JOptionPane.showMessageDialog(null,
                textoAviso,
                "Controle de Estoque",
                javax.swing.JOptionPane.WARNING_MESSAGE);

          } else {
            // Se chegou aqui, o produto existe e tem estoque!
            double valorTotalItem = quantDesejada * precoUnitario;

            // A) Salvar o item vendido no arquivo Pedidos.csv 
            String linhaPedido = cpfCliente + ";" + codProdBusca + ";" + quantDesejada + ";" + valorTotalItem;
            savePed(linhaPedido);

            // B) Atualizar o estoque alterando o arquivo Produtos.csv usando um temporário
            Arquivo tempProdEstoque = new Arquivo("Produtos_temp.csv");

            produtos.abrirLeitura();
            tempProdEstoque.abrirEscrita(true);

            linha = produtos.lerLinha();
            while (linha != null) {
              String[] dadosPedP = new String[5];
              int PossPedP1, PossPedP2 = 0;
                  PossPedP1 = 0;
                  PossPedP2 = linha.indexOf(';',PossPedP1);
                  dadosPedP[0] = linha.substring(PossPedP1, PossPedP2);

                  PossPedP1 = PossPedP2 + 1;
                  PossPedP2 = linha.indexOf(';',PossPedP1);
                  dadosPedP[1] = linha.substring(PossPedP1, PossPedP2);

                  PossPedP1 = PossPedP2 + 1;
                  PossPedP2 = linha.indexOf(';',PossPedP1);
                  dadosPedP[2] = linha.substring(PossPedP1, PossPedP2);
                  
                  PossPedP1 = PossPedP2 + 1;
                  dadosPedP[3] = linha.substring(PossPedP1);

              if (Integer.parseInt(dadosPedP[0]) == codProdBusca) {
                int novoEstoque = estoqueAtual - quantDesejada;
                dadosPedP[3] = String.valueOf(novoEstoque);
              }

              String novaLinhaProd = dadosPedP[0] + ";" + dadosPedP[1] + ";" + dadosPedP[2] + ";" + dadosPedP[3] + ";" + dadosPedP[4];
              tempProdEstoque.escreverLinha(novaLinhaProd);

              linha = produtos.lerLinha();
            }
            produtos.fecharArquivo();
            tempProdEstoque.fecharArquivo();

            // Substitui com segurança o arquivo de produtos
            produtos.excluirArquivo();
            tempProdEstoque.renomearArquivo("Produtos.csv");

            // Reinicializa a variável para que a próxima leitura funcione perfeitamente
            produtos = new Arquivo("Produtos.csv");

            System.out.println(
                "Item \"" + nomeProduto + "\" adicionado ao pedido com sucesso! Subtotal: R$ " + valorTotalItem);
          }

          // Controlamos se o loop de itens continua ou para através do método existente
          // da classe Entrada
          continuarPedido = Entrada
              .leiaInt(menuJanela("CONTINUAR/FINALIZAR PEDIDO") + "\nDeseja incluir mais um item neste pedido?\n1 - Sim\n2 - Não (Encerrar Pedido)");
        }

        System.out.println(menu("PEDIDO FINALIZANDO PARA O CLIENTE CPF: " + cpfCliente));
        break;
        case(2):
        relPed(pedidos);
        break;
        case(3):
        System.out.println(menu("VOLTANDO A TELA INICIAL"));
        break;
      }
      }while(Ped != 3);
        break;
      // fim do programa.
      case (3):
        System.out.println(menu("FINALIZANDO O PROGRAMA..."));
        break;
    }
  }
  while (i != 3);
      
  }
}
