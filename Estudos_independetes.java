
public class Estudos_independetes {
  public static void main(String[] args) {
    Arquivo produtos = new Arquivo("Produtos.csv");
    Arquivo pedidos = new Arquivo("Pedidos.csv");
    String linha, nome, /* cpf */grupo;
    int estoque = 0;
    int codProd = 1;
    double precoVend = 0;
    int i = 0;

    // Inicio do programa, vao perguntar a qual parte do sistema voce quer acessar.
    i = Entrada.leiaInt(
        "Digite a opcao que voce quer acessar\n1 - Cadastro dos produtos\n2 - Pedidos.\n3 - Finalizar o programa.");
    switch (i) {
      // Cadastro de produtos.
      case (1):
        int pr = 0;
        pr = Entrada.leiaInt(
            "1 - Cadastrar novo produto\n2 - Ajuste no cadastro\n3 - Consultar produto\n4 - Retornar ao menu principal.");
        switch (pr) {
          // Cadastrar novo produto.
          case (1):
            // Parte do codigo para identificar qual o proximo dos produtos
            produtos.abrirLeitura();
            linha = produtos.lerLinha();
            while (linha != null) {
              codProd++;
              linha = produtos.lerLinha();
            }
            produtos.fecharArquivo();
            produtos.abrirEscrita(true);
            nome = Entrada.leiaString("Escreva o nome do novo Produto");
            grupo = Entrada.leiaString("Escreva de qual grupo o produto se encontra ");
            estoque = Entrada.leiaInt("Escreva a quantidade inicial do estoque");
            precoVend = Entrada.leiaDouble("Escreva qual o valor de venda do produto");
            linha = codProd + ";" + nome + ";" + grupo + ";" + estoque + ";" + precoVend;
            produtos.escreverLinha(linha);
            produtos.fecharArquivo();
            break;

          // Ajustes no cadastro.
          case (2):
            int pr_aj = 0;
            pr_aj = Entrada.leiaInt("1 - Nome\n2 - Preco\n3 - Quantidade no estoque\n4 - Grupo\n5 - Voltar. ");
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
            while (pr_aj != 5)
              ;
            break;

          // Consultar produto.
          case (3):
            int pr_con = 0;

            pr_con = Entrada
                .leiaInt("Escolha a forma que voce quer pesquisar\n1 - Codigo\n2 - nome\n2 - Grupo\n4 - Voltar");

            switch (pr_con) {
              // Pesquisa por codigo ( pelo numero de produtos que for cadastrando).
              case (1):

                int codigo = Entrada.leiaInt("Digite o codigo do produto:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();

                boolean encontrou = false;

                while (linha != null) {
                  String dados[] = linha.split(";");

                  if (Integer.parseInt(dados[0]) == codigo) {
                    System.out.println("Codigo: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Grupo: " + dados[2]);
                    System.out.println("Estoque: " + dados[3]);
                    System.out.println("Preco: " + dados[4]);

                    encontrou = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!encontrou) {
                  System.out.println("Produto nao encontrado.");
                }

                break;

              // Pesquisa por nome.
              case (2):

                String nomeBusca = Entrada.leiaString("Digite o nome:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();

                boolean achouNome = false;

                while (linha != null) {
                  String dados[] = linha.split(";");

                  if (dados[1].toLowerCase().contains(nomeBusca.toLowerCase())) {
                    System.out.println("Codigo: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Grupo: " + dados[2]);
                    System.out.println("Estoque: " + dados[3]);
                    System.out.println("Preco: " + dados[4]);

                    achouNome = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouNome) {
                  System.out.println("Nenhum produto encontrado.");
                }

                break;

              // Pesquisa pelo grupo(Tres todos os produtos do grupo).
              case (3):

                String grupoBusca = Entrada.leiaString("Digite o grupo:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();

                boolean achouGrupo = false;

                while (linha != null) {
                  String dados[] = linha.split(";");

                  if (dados[2].equalsIgnoreCase(grupoBusca)) {
                    System.out.println("Codigo: " + dados[0]);
                    System.out.println("Nome: " + dados[1]);
                    System.out.println("Grupo: " + dados[2]);
                    System.out.println("Estoque: " + dados[3]);
                    System.out.println("Preco: " + dados[4]);

                    achouGrupo = true;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouGrupo) {
                  System.out.println("Nenhum produto encontrado.");
                }

                break;

              // Finalizar a tela de consulta
              case (4):
                System.out.println("Voltando...");
                break;

              default:
                System.out.println("Opcao invalida");
                break;
            }

            while (pr_con != 4)
              ;
            break;

          // Finalizar aba de produtos.
          case (4):
            System.out.println("Voltando para a tela incial");
            break;

          // Identidficador para ver se a op??o ? real
          default:
            System.out.println("Opcao invalida");
            break;
        }

        // Esse while serve para repetir a pergunta dos produtos at? ser digitado 4.
        while (pr != 4)
          ;
        break;

      // Pedidos
      case (2):
        System.out.println("--- Área de Pedidos / Vendas ---");
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
            String[] dadosProd = linha.split(";");

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
            pedidos.abrirEscrita(true);
            String linhaPedido = cpfCliente + ";" + codProdBusca + ";" + quantDesejada + ";" + valorTotalItem;
            pedidos.escreverLinha(linhaPedido);
            pedidos.fecharArquivo();

            // B) Atualizar o estoque alterando o arquivo Produtos.csv usando um temporário
            Arquivo tempProdEstoque = new Arquivo("Produtos_temp.csv");

            produtos.abrirLeitura();
            tempProdEstoque.abrirEscrita(true);

            linha = produtos.lerLinha();
            while (linha != null) {
              String[] dados = linha.split(";");

              if (Integer.parseInt(dados[0]) == codProdBusca) {
                int novoEstoque = estoqueAtual - quantDesejada;
                dados[3] = String.valueOf(novoEstoque);
              }

              String novaLinhaProd = dados[0] + ";" + dados[1] + ";" + dados[2] + ";" + dados[3] + ";" + dados[4];
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
              .leiaInt("\nDeseja incluir mais um item neste pedido?\n1 - Sim\n2 - Não (Encerrar Pedido)");
        }

        System.out.println("--- Pedido finalizado para o cliente CPF: " + cpfCliente + " ---");
        break;
      // fim do programa.
      case (3):
        System.out.println("Finalizando o programa...");
        break;
    }
    while (i != 3)
      ;
  }
}
