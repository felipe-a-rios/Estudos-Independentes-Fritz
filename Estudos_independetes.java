
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
  public static void main(String[] args) {
    Arquivo produtos = new Arquivo("Produtos.csv");
    Arquivo pedidos = new Arquivo("Pedidos.csv");
    String linha, nome, /* cpf */grupo,nome_sis;
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
            nome_sis = nome.toLowerCase();
            grupo = Entrada.leiaString("Escreva de qual grupo o produto se encontra ");
            estoque = Entrada.leiaInt("Escreva a quantidade inicial do estoque");
            precoVend = Entrada.leiaDouble("Escreva qual o valor de venda do produto");
            linha = codProd + ";" + nome + ";" + grupo + ";" + estoque + ";" + precoVend + ";" + nome_sis;
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
            Relatorios.relsProd(produtos);
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

              if (quantDesejada > 0 && estoqueAtual >= quantDesejada) {
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
                  PossPedP2 = linha.indexOf(';',PossPedP1);
                  dadosPedP[3] = linha.substring(PossPedP1, PossPedP2);
                  
                  PossPedP1 = PossPedP2 + 1;
                  dadosPedP[4] = linha.substring(PossPedP1);

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
        Relatorios.relPed(pedidos);
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
  System.exit(0);
  }
}
