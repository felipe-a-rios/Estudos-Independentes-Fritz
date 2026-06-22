
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
          case(2):
          int pr_aj_novo = 0;
                do {
                  pr_aj_novo = Entrada.leiaInt(menuJanela("AJUSTE CADASTRO") + "\n1 - Nome\n2 - Preco\n3 - Quantidade no estoque\n4 - Grupo\n5 - Voltar. ");
                  
                  if (pr_aj_novo >= 1 && pr_aj_novo <= 4) {
                    int codAltera = Entrada.leiaInt("Digite o código do produto que deseja alterar:");
                    
                    Arquivo tempAj = new Arquivo("Produtos_temp.csv");
                    produtos.abrirLeitura();
                    tempAj.abrirEscrita(true);

                    boolean achou = false;
                    linha = produtos.lerLinha();
                    
                    while (linha != null) {
                      int p1, p2;
                      
                      p1 = 0;
                      p2 = linha.indexOf(';', p1);
                      String cCodigo = linha.substring(p1, p2);

                      p1 = p2 + 1;
                      p2 = linha.indexOf(';', p1);
                      String cNome = linha.substring(p1, p2);

                      p1 = p2 + 1;
                      p2 = linha.indexOf(';', p1);
                      String cGrupo = linha.substring(p1, p2);

                      p1 = p2 + 1;
                      p2 = linha.indexOf(';', p1);
                      String cEstoque = linha.substring(p1, p2);

                      p1 = p2 + 1;
                      p2 = linha.indexOf(';', p1);
                      String cPreco = linha.substring(p1, p2);

                      p1 = p2 + 1;
                      String cNomeSis = linha.substring(p1);

                      if (Integer.parseInt(cCodigo) == codAltera) {
                        achou = true;
                        if (pr_aj_novo == 1) {
                          cNome = Entrada.leiaString("Digite o novo NOME do produto:");
                          cNomeSis = cNome.toLowerCase();
                        } else if (pr_aj_novo == 2) {
                          cPreco = String.valueOf(Entrada.leiaDouble("Digite o novo PREÇO de venda:"));
                        } else if (pr_aj_novo == 3) {
                          cEstoque = String.valueOf(Entrada.leiaInt("Digite a nova QUANTIDADE em estoque:"));
                        } else if (pr_aj_novo == 4) {
                          cGrupo = Entrada.leiaString("Digite o novo GRUPO do produto:");
                        }
                      }

                      String novaLinha = cCodigo + ";" + cNome + ";" + cGrupo + ";" + cEstoque + ";" + cPreco + ";" + cNomeSis;
                      tempAj.escreverLinha(novaLinha);
                      
                      linha = produtos.lerLinha();
                    }
                    
                    produtos.fecharArquivo();
                    tempAj.fecharArquivo();

                    produtos.excluirArquivo();
                    tempAj.renomearArquivo("Produtos.csv");
                    produtos = new Arquivo("Produtos.csv");

                    if (achou) {
                      javax.swing.JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
                    } else {
                      javax.swing.JOptionPane.showMessageDialog(null, "Erro: Código do produto não encontrado!", "Aviso", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                  }

                  if (pr_aj_novo == 5) {
                    System.out.println("Voltando a tela inicial");
                  } else if (pr_aj_novo < 1 || pr_aj_novo > 5) {
                    System.out.println("Opcao invalida");
                  }
                } while (pr_aj_novo != 5);
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
          
          // --- GERAÇÃO DO CÓDIGO DO PEDIDO ---
          // Lemos o arquivo Pedidos.csv para descobrir qual foi o último código gerado.
          int codPedidoAtual = 1;
          pedidos.abrirLeitura();
          linha = pedidos.lerLinha();
          while (linha != null) {
              int p1 = 0;
              int p2 = linha.indexOf(';', p1);
              if (p2 != -1) {
                  String codStr = linha.substring(p1, p2);
                  try {
                      int codLido = Integer.parseInt(codStr);
                      // Se o código lido for maior ou igual, o nosso próximo código deve ser maior
                      if (codLido >= codPedidoAtual) {
                          codPedidoAtual = codLido + 1;
                      }
                  } catch (Exception e) {
                      // Ignora linhas formatadas incorretamente
                  }
              }
              linha = pedidos.lerLinha();
          }
          pedidos.fecharArquivo();
          // -----------------------------------

          String cpfCliente = Entrada.leiaString("Digite o CPF do cliente:");
          int continuarPedido = 1; // Variável de controle do laço de itens

          // LAÇO PRINCIPAL DO PEDIDO: Permite incluir vários itens sob o mesmo Código de Pedido
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
              PossPed2 = linha.indexOf(';', PossPed1);
              dadosProd[0] = linha.substring(PossPed1, PossPed2);

              PossPed1 = PossPed2 + 1;
              PossPed2 = linha.indexOf(';', PossPed1);
              dadosProd[1] = linha.substring(PossPed1, PossPed2);

              PossPed1 = PossPed2 + 1;
              PossPed2 = linha.indexOf(';', PossPed1);
              dadosProd[2] = linha.substring(PossPed1, PossPed2);
                    
              PossPed1 = PossPed2 + 1;
              PossPed2 = linha.indexOf(';', PossPed1);
              dadosProd[3] = linha.substring(PossPed1, PossPed2);

              PossPed1 = PossPed2 + 1;
              dadosProd[4] = linha.substring(PossPed1);

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
              javax.swing.JOptionPane.showMessageDialog(null,
                  "Erro: Produto não encontrado!",
                  "Aviso do Sistema",
                  javax.swing.JOptionPane.ERROR_MESSAGE);

            } else if (!estoqueSuficiente) {
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

              // A) Salvar o item vendido no arquivo Pedidos.csv NO NOVO FORMATO (Com CodPedido no Início)
              String linhaPedido = codPedidoAtual + ";" + cpfCliente + ";" + codProdBusca + ";" + quantDesejada + ";" + valorTotalItem;
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
                PossPedP2 = linha.indexOf(';', PossPedP1);
                dadosPedP[0] = linha.substring(PossPedP1, PossPedP2);

                PossPedP1 = PossPedP2 + 1;
                PossPedP2 = linha.indexOf(';', PossPedP1);
                dadosPedP[1] = linha.substring(PossPedP1, PossPedP2);

                PossPedP1 = PossPedP2 + 1;
                PossPedP2 = linha.indexOf(';', PossPedP1);
                dadosPedP[2] = linha.substring(PossPedP1, PossPedP2);
                    
                PossPedP1 = PossPedP2 + 1;
                PossPedP2 = linha.indexOf(';', PossPedP1);
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
              produtos = new Arquivo("Produtos.csv");

              System.out.println(menu(
                  "Item \"" + nomeProduto + "\" adicionado ao Pedido Nº " + codPedidoAtual + " com sucesso! Subtotal do item: R$ " + valorTotalItem));
            }

            continuarPedido = Entrada.leiaInt(menuJanela("CONTINUAR/FINALIZAR PEDIDO") + "\nDeseja incluir mais um item neste pedido?\n1 - Sim\n2 - Não (Encerrar Pedido)");
          }

          System.out.println(menu("PEDIDO Nº " + codPedidoAtual + " FINALIZADO PARA O CLIENTE CPF: " + cpfCliente));
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
