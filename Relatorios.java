public class Relatorios {
//Função relatório de pedidos
public static void relPed(Arquivo pedidos){
  int rPed, Poss1, Poss2= 0;
  String linha = "";
  int validRelPed = 0;
  int contPed = 0;

  // Conta quantas linhas tem para preparar as matrizes corretamente sem limite travado
  pedidos.abrirLeitura();
  linha = pedidos.lerLinha();
  while (linha != null){
    contPed++;
    linha = pedidos.lerLinha();
  }
  pedidos.fecharArquivo();
  linha = "";

  do {
    rPed = Entrada.leiaInt(Estudos_independetes.menuJanela("RELATÓRIO PEDIDOS") + "Escolha a opção que você quer acessar\n1- Todos os pedidos \n2- Ped. P/Clientes \n3- Ped. P/Produto \n4- Voltar");
    switch(rPed){
      
      // ==========================================
      // CASO 1: TODOS OS PEDIDOS (AGRUPADOS)
      // ==========================================
      case (1):
        pedidos.abrirLeitura();
        linha = pedidos.lerLinha();
        double valorTotGeral = 0;

        // Criando matriz baseada na quantidade exata de itens lidos (5 campos novos)
        // [0] CodPed | [1] CPF | [2] CodProd | [3] Quant | [4] ValorTotalItem
        String dadosRelPed[][] = new String[contPed][5];
        int i = 0;

        while (linha != null && i < contPed) {
          Poss1 = 0;
          Poss2 = linha.indexOf(';',Poss1);
          dadosRelPed[i][0] = linha.substring(Poss1, Poss2); // CodPedido

          Poss1 = Poss2 + 1;
          Poss2 = linha.indexOf(';',Poss1);
          dadosRelPed[i][1] = linha.substring(Poss1, Poss2); // CPF

          Poss1 = Poss2 + 1;
          Poss2 = linha.indexOf(';',Poss1);
          dadosRelPed[i][2] = linha.substring(Poss1, Poss2); // CodProd

          Poss1 = Poss2 + 1;
          Poss2 = linha.indexOf(';',Poss1);
          dadosRelPed[i][3] = linha.substring(Poss1, Poss2); // Quantidade

          Poss1 = Poss2 + 1;
          dadosRelPed[i][4] = linha.substring(Poss1); // Valor

          i++;
          linha = pedidos.lerLinha();
        }
        pedidos.fecharArquivo();

        String pedidoAtual = "";
        double valorTotalPedidoAtual = 0;

        System.out.println(Estudos_independetes.menu("TODOS OS PEDIDOS"));

        // Laço de repetição para printar agrupado pelo Código do Pedido
        for(int k = 0; k < contPed; k++) {
            // Verifica se o código do pedido mudou para printar o cabeçalho
            if (!dadosRelPed[k][0].equals(pedidoAtual)) {
                // Se não for o primeiro laço, finaliza o pedido anterior imprimindo o total dele
                if (!pedidoAtual.equals("")) {
                    System.out.println("------------------------------------------------------");
                    System.out.println("TOTAL DO PEDIDO " + pedidoAtual + ": R$ " + valorTotalPedidoAtual);
                    System.out.println("======================================================\n");
                }
                pedidoAtual = dadosRelPed[k][0];
                valorTotalPedidoAtual = 0; // Zera o contador do pedido para o próximo
                
                System.out.println("CÓDIGO DO PEDIDO: " + pedidoAtual + " | CPF: " + dadosRelPed[k][1]);
                System.out.println("PRODUTOS DO PEDIDO:");
            }
            
            // Printa os itens separadamente
            System.out.println(" -> Cód. Prod: " + dadosRelPed[k][2] + " | Qtd: " + dadosRelPed[k][3] + " | Subtotal Item: R$ " + dadosRelPed[k][4]);
            
            double valConvert = Double.parseDouble(dadosRelPed[k][4]);
            valorTotalPedidoAtual += valConvert;
            valorTotGeral += valConvert; // Conta para o acumulado final do dia/sistema
        }
        
        // Printa o total do ÚLTIMO pedido processado
        if (!pedidoAtual.equals("")) {
            System.out.println("------------------------------------------------------");
            System.out.println("TOTAL DO PEDIDO " + pedidoAtual + ": R$ " + valorTotalPedidoAtual);
            System.out.println("======================================================\n");
        }

        System.out.println("VALOR TOTAL ARRECADADO DE TODOS OS PEDIDOS: R$ " + valorTotGeral);
        System.out.println("======================================================\n");

        while(validRelPed != 1 && validRelPed != 2){
            validRelPed = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
            if(validRelPed == 1){
                System.out.println(saveRelPed(dadosRelPed, contPed, valorTotGeral));
            }
            if(validRelPed == 2){
                break;
            }
            if(validRelPed != 1 && validRelPed != 2){
                System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
            }
        }
      break;

      // ==========================================
      // CASO 2: PEDIDOS POR CLIENTE (Revisado para os 5 campos)
      // ==========================================
      case (2):
        String nomeBusca = Entrada.leiaString(Estudos_independetes.menuJanela("CONSULTA POR CLIENTE")+"Digite o CPF:");

        pedidos.abrirLeitura();
        linha = pedidos.lerLinha();
        double valorTotCli = 0;
        boolean achouCPF = false;
        String dadosCPF[] = new String[5]; // 5 Campos agora

        System.out.println(Estudos_independetes.menu("PEDIDOS DO CLIENTE"));

        while (linha != null) {
            Poss1 = 0;
            Poss2 = linha.indexOf(';',Poss1);
            dadosCPF[0] = linha.substring(Poss1, Poss2); // CodPedido

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosCPF[1] = linha.substring(Poss1, Poss2); // CPF

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosCPF[2] = linha.substring(Poss1, Poss2); // CodProd

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosCPF[3] = linha.substring(Poss1, Poss2); // Quant
            
            Poss1 = Poss2 + 1;
            dadosCPF[4] = linha.substring(Poss1); // Valor

            if (dadosCPF[1].toLowerCase().contains(nomeBusca.toLowerCase())) {
                System.out.println("Pedido Nº: " + dadosCPF[0] + " | Cód. Produto: " + dadosCPF[2] + " | Quantidade: " + dadosCPF[3] + " | Valor: R$ " + dadosCPF[4]);
                
                double valConvert = Double.parseDouble(dadosCPF[4]);
                valorTotCli += valConvert;
                achouCPF = true;
            }
            linha = pedidos.lerLinha();
        }
        
        if (achouCPF){
            System.out.println("======================================================");
            System.out.println("VALOR TOTAL GASTO PELO CPF (" + nomeBusca + "): R$ " + valorTotCli);
            System.out.println("======================================================\n");
        } else {
            System.out.println(Estudos_independetes.menu("CPF NÃO ENCONTRADO"));
        }
        pedidos.fecharArquivo();
      break;

      // ==========================================
      // CASO 3: PEDIDOS POR PRODUTO (Revisado para os 5 campos)
      // ==========================================
      case (3):
        int CodProdRel = Entrada.leiaInt(Estudos_independetes.menuJanela("CONSULTA POR PRODUTO") + "Digite Código do Produto");
        
        pedidos.abrirLeitura();
        linha = pedidos.lerLinha();
        double valorTotProd = 0;
        String dadosProd[] = new String[5];

        System.out.println(Estudos_independetes.menu("PEDIDOS COM O PRODUTO CÓDIGO " + CodProdRel));

        while (linha != null) {
            Poss1 = 0;
            Poss2 = linha.indexOf(';',Poss1);
            dadosProd[0] = linha.substring(Poss1, Poss2); // CodPed

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosProd[1] = linha.substring(Poss1, Poss2); // CPF

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosProd[2] = linha.substring(Poss1, Poss2); // CodProd

            Poss1 = Poss2 + 1;
            Poss2 = linha.indexOf(';',Poss1);
            dadosProd[3] = linha.substring(Poss1, Poss2); // Quant

            Poss1 = Poss2 + 1;
            dadosProd[4] = linha.substring(Poss1);  // Valor

            if (Integer.parseInt(dadosProd[2]) == CodProdRel){
                System.out.println("Pedido Nº: " + dadosProd[0] + " | CPF: " + dadosProd[1] + " | Quantidade: " + dadosProd[3] + " | Valor: R$ " + dadosProd[4]);
                double valConvert = Double.parseDouble(dadosProd[4]);
                valorTotProd += valConvert;
            }
            linha = pedidos.lerLinha();
        }
        System.out.println("======================================================");
        System.out.println("VALOR TOTAL ARRECADADO COM ESTE PRODUTO: R$ " + valorTotProd);
        System.out.println("======================================================\n");
        pedidos.fecharArquivo();
      break;

      case (4):
        System.out.println(Estudos_independetes.menu("VOLTANDO"));
      break;
      
      default:
        System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
      break;
    }
  } while (rPed != 4);
}
//Função de salvar o relatório COM O NOVO AGRUPAMENTO
public static String saveRelPed(String[][] dadosRelPed, int contPed, double valorTotGeral){
    Arquivo relatorio = new Arquivo("Relatório_Pedidos.txt");
    Arquivo produtos = new Arquivo("Produtos.csv");
    String linha = "";
    String CodPed, CPF, CodProd, Quant, ValorTotItem, CodArq;
    String NomeProd = "";
    int Poss1, Poss2 = 0;

    relatorio.abrirEscrita(); // Abre e substitui o arquivo antigo
    linha = ("==========================================================================================================================\n"+
             "\n"+
             "                                     \t\t    RELATÓRIO DE PEDIDOS\n"+
             "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();

    String pedidoAtual = "";
    double valorTotalPedidoAtual = 0;

    for(int i= 0; i < contPed; i++){
        CodPed = dadosRelPed[i][0];
        CPF = dadosRelPed[i][1];
        CodProd = dadosRelPed[i][2];
        Quant = dadosRelPed[i][3];
        ValorTotItem = dadosRelPed[i][4];

        // Busca o nome do produto no arquivo Produtos.csv para deixar o relatório mais bonito
        produtos.abrirLeitura();
        linha = produtos.lerLinha();
        NomeProd = "Desconhecido"; // Default
        while(linha != null){
            Poss1 = 0;
            Poss2 = linha.indexOf(';', Poss1);
            CodArq = linha.substring(Poss1,Poss2);
            if(CodArq.equals(CodProd)){
                Poss1 = Poss2 + 1;
                Poss2 = linha.indexOf(';',Poss1);
                NomeProd = linha.substring(Poss1, Poss2);
                break;
            }
            linha = produtos.lerLinha();
        }
        produtos.fecharArquivo();

        relatorio.abrirEscrita(true); // Continua escrevendo no final do arquivo (append)

        // Se o pedido mudou, imprime o cabeçalho dele e fecha o valor do anterior
        if (!CodPed.equals(pedidoAtual)) {
            if (!pedidoAtual.equals("")) {
                linha = ("--------------------------------------------------------------------------------------------------------------------------\n" +
                         "TOTAL DO PEDIDO " + pedidoAtual + ": R$ " + valorTotalPedidoAtual + "\n" +
                         "==========================================================================================================================\n");
                relatorio.escreverLinha(linha);
            }
            pedidoAtual = CodPed;
            valorTotalPedidoAtual = 0;

            linha = ("\nCÓDIGO DO PEDIDO: " + CodPed + "       CPF CLIENTE: " + CPF + "\nITENS DO PEDIDO:\n");
            relatorio.escreverLinha(linha);
        }

        // Imprime os itens
        linha = ("  -> Cód. Produto: " + CodProd +"   |   Nome Prod.: "+ NomeProd +"   |   Qtd: " + Quant + "   |   Subtotal: R$ " + ValorTotItem);
        relatorio.escreverLinha(linha);
        relatorio.fecharArquivo();
        
        valorTotalPedidoAtual += Double.parseDouble(ValorTotItem);
    }

    // Fechando o valor total do último pedido do laço
    relatorio.abrirEscrita(true);
    if (!pedidoAtual.equals("")) {
        linha = ("--------------------------------------------------------------------------------------------------------------------------\n" +
                 "TOTAL DO PEDIDO " + pedidoAtual + ": R$ " + valorTotalPedidoAtual + "\n" +
                 "==========================================================================================================================\n");
        relatorio.escreverLinha(linha);
    }

    // Informação final (Soma de TUDO)
    linha = ("\nVALOR TOTAL DE TODOS OS PEDIDOS: R$ " + valorTotGeral + "\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();

    return Estudos_independetes.menu("RELATÓRIO SALVO COM SUCESSO EM 'Relatório_Pedidos.txt'");
}
//Função para salvar o relatório de produtos
public static String saveRelProdN(String[][] dadosRelProdN, int contProdN){
    Arquivo relatorio = new Arquivo("Produtos-"+dadosRelProdN[0][0]+".txt");
    String linha = "";
    String CodProd,Nome,Grupo,Qunt,Preco;

    relatorio.abrirEscrita();
    linha = ("==========================================================================================================================\n"+
            "\n"+
             "                                     \t\t  PRODUTO " + dadosRelProdN[0][0] + "\n" +
            "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    linha = "";
    for(int i= 0; i < contProdN; i++){
      CodProd = dadosRelProdN[i][0];
      Nome = dadosRelProdN[i][1];
      Grupo = dadosRelProdN[i][2];
      Qunt = dadosRelProdN[i][3];
      Preco = dadosRelProdN[i][4];
    relatorio.abrirEscrita(true);
    linha = ("Código do Produto: " + CodProd + "        Nome: " + Nome + "      Grupo: " + Grupo +
             "\n"+
             "Quantidade: " + Qunt + " 		Preço de Venda: " + Preco + "\n"+
             "\n"+
             "==========================================================================================================================\n"+
             " ");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    }
return Estudos_independetes.menu("RELATÓRIO SALVO COM SUCESSO");
}
//Função para salvar o relatório de pedidos por código do produto
public static String saveRelProdC(String[] dadosCodProd){
    Arquivo relatorio = new Arquivo("Produtos-"+dadosCodProd[0]+".txt");
    String linha = "";
    String CodProd,Nome,Grupo,Qunt,Preco;

    relatorio.abrirEscrita();
    linha = ("==========================================================================================================================\n"+
            "\n"+
             "                                     \t\t  PRODUTO " + dadosCodProd[0] + "\n" +
            "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    linha = "";
      CodProd = dadosCodProd[0];
      Nome = dadosCodProd[1];
      Grupo = dadosCodProd[2];
      Qunt = dadosCodProd[3];
      Preco = dadosCodProd[4];
    relatorio.abrirEscrita(true);
    linha = ("Código do Produto: " + CodProd + "        Nome: " + Nome + "      Grupo: " + Grupo +
             "\n"+
             "Quantidade: " + Qunt + " 		Preço de Venda: " + Preco + "\n"+
             "\n"+
             "==========================================================================================================================\n"+
             " ");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
return Estudos_independetes.menu("RELATÓRIO SALVO COM SUCESSO");
}
public static String saveRelProdG(String[][] dadosRelProdG, int contProdG){
    Arquivo relatorio = new Arquivo("Produtos-"+dadosRelProdG[0][0]+".txt");
    String linha = "";
    String CodProd,Nome,Grupo,Qunt,Preco;

    relatorio.abrirEscrita();
    linha = ("==========================================================================================================================\n"+
            "\n"+
             "                                     \t\t  PRODUTO " + dadosRelProdG[0][0] + "\n" +
            "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    linha = "";
    for(int i= 0; i < contProdG; i++){
      CodProd = dadosRelProdG[i][0];
      Nome = dadosRelProdG[i][1];
      Grupo = dadosRelProdG[i][2];
      Qunt = dadosRelProdG[i][3];
      Preco = dadosRelProdG[i][4];
    relatorio.abrirEscrita(true);
    linha = ("Código do Produto: " + CodProd + "        Nome: " + Nome + "      Grupo: " + Grupo +
             "\n"+
             "Quantidade: " + Qunt + " 		Preço de Venda: " + Preco + "\n"+
             "\n"+
             "==========================================================================================================================\n"+
             " ");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    }
return Estudos_independetes.menu("RELATÓRIO SALVO COM SUCESSO");
}
//Função para trazer um relatório dos grupos existentes, para que o usuário não fique precisando adivinhar 
//quais estão cadastrados
public static void relGrupos(Arquivo produtos){
    System.out.println(Estudos_independetes.menu("\nRELATÓRIO DE GRUPOS CADASTRADOS"));

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
}
public static void relsProd(Arquivo produtos){
        String linha = "";
        int pr_con = 0;
            do {

            pr_con = Entrada
                .leiaInt(Estudos_independetes.menuJanela("CONSULTA PRODUTOS") + "Escolha a forma que voce quer pesquisar\n1 - Codigo\n2 - nome\n3 - Grupo\n4 - Voltar");

            switch (pr_con)  {
              // Pesquisa por codigo ( pelo numero de produtos que for cadastrando).
              case (1):

                int codigo = Entrada.leiaInt(Estudos_independetes.menuJanela("CONSULTA POR CÓDIGO")+"Digite o codigo do produto:");
                

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int Poss1, Poss2 =0;
                String dadosCodProd[] = new String[6];
                int valideRelProdC = 0;
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
                  Poss2 = linha.indexOf(';',Poss1);
                  dadosCodProd[4] = linha.substring(Poss1, Poss2);

                  Poss1 = Poss2 + 1;
                  dadosCodProd[5] = linha.substring(Poss1);

                  if (Integer.parseInt(dadosCodProd[0]) == codigo) {
                    System.out.println("Codigo: " + dadosCodProd[0]);
                    System.out.println("Nome: " + dadosCodProd[1]);
                    System.out.println("Grupo: " + dadosCodProd[2]);
                    System.out.println("Estoque: " + dadosCodProd[3]);
                    System.out.println("Preco: " + dadosCodProd[4]);

                    encontrou = true;
                    break;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();
                

                if (!encontrou) {       
                  System.out.println(Estudos_independetes.menu("PRODUTO NÂO ENCONTRADO"));
                }

                
                 while(valideRelProdC != 1 && valideRelProdC != 2){
                valideRelProdC = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
                if(valideRelProdC == 1){
                  System.out.println(saveRelProdC(dadosCodProd));
                }

                if(valideRelProdC == 2){
                  break;
                }

                if(valideRelProdC != 1 && valideRelProdC != 2){
                  System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
                }
              }
                

                break;


              // Pesquisa por nome.
              case (2):

                String nomeBusca = Entrada.leiaString(Estudos_independetes.menuJanela("CONSULTA POR NOME")+"Digite o nome:").toLowerCase();
                int PossN1, PossN2,j =0;
                String dadosNomProd[] = new String[6];
                int contProdN = 0;
                int validRelProdN = 0;
                produtos.abrirLeitura();
                linha = produtos.lerLinha();
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
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[4] = linha.substring(PossN1,PossN2);
                  
                  PossN1 = PossN2 + 1;
                  dadosNomProd[5] = linha.substring(PossN1);

                  if (dadosNomProd[5].contains(nomeBusca)) {
                    contProdN++;
                  }
                  linha = produtos.lerLinha();
                }
                produtos.fecharArquivo();

                produtos.abrirLeitura();
                linha = produtos.lerLinha();

                String dadosRelProdN[][] = new String [contProdN][6];
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
                  PossN2 = linha.indexOf(';',PossN1);
                  dadosNomProd[4] = linha.substring(PossN1,PossN2);
                  
                  PossN1 = PossN2 + 1;
                  dadosNomProd[5] = linha.substring(PossN1);

                  if (dadosNomProd[5].contains(nomeBusca)) {
                    System.out.println("Codigo: " + dadosNomProd[0]);
                    System.out.println("Nome: " + dadosNomProd[2]);
                    System.out.println("Grupo: " + dadosNomProd[1]);
                    System.out.println("Estoque: " + dadosNomProd[3]);
                    System.out.println("Preco: " + dadosNomProd[4]);
                    
                    dadosRelProdN[j][0] = dadosNomProd[0];
                    dadosRelProdN[j][1] = dadosNomProd[2];
                    dadosRelProdN[j][2] = dadosNomProd[1];
                    dadosRelProdN[j][3] = dadosNomProd[3];
                    dadosRelProdN[j][4] = dadosNomProd[4];

                    achouNome = true;
                    j++;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouNome) {
                  System.out.println(Estudos_independetes.menu("NENHUM PRODUTO ENCONTRADO"));
                }
                 while(validRelProdN != 1 && validRelProdN != 2){
                validRelProdN = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
                if(validRelProdN == 1){
                  System.out.println(saveRelProdN(dadosRelProdN, contProdN));
                }

                if(validRelProdN == 2){
                  break;
                }

                if(validRelProdN != 1 && validRelProdN != 2){
                  System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
                }
              }

                break;

              // Pesquisa pelo grupo(Tres todos os produtos do grupo).
              case (3):

                String grupoBusca = Entrada.leiaString(Estudos_independetes.menuJanela("CONSULTA POR GRUPO")+"Digite o grupo:");
                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int PossG1, PossG2, g = 0;
                String dadosGrupProd[] = new String[6];
                int contProdGroup = 0;
                int validRelProdG = 0;
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
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[4] = linha.substring(PossG1, PossG2);

                  PossG1 = PossG2 + 1;
                  dadosGrupProd[5] = linha.substring(PossG1);

                  if (dadosGrupProd[2].equalsIgnoreCase(grupoBusca)) {
                    contProdGroup++;
                  }
                  linha = produtos.lerLinha();
                 }
                 produtos.fecharArquivo();
                 String dadosRelProdG[][] = new String[contProdGroup][5];
                 produtos.abrirLeitura();
                 linha = produtos.lerLinha();
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
                  PossG2 = linha.indexOf(';',PossG1);
                  dadosGrupProd[4] = linha.substring(PossG1, PossG2);
                  
                  PossG1 = PossG2 + 1;
                  dadosGrupProd[5] = linha.substring(PossG1);

                  if (dadosGrupProd[2].trim().equalsIgnoreCase(grupoBusca.trim())) {
                    System.out.println("Codigo: " + dadosGrupProd[0]);
                    System.out.println("Nome: " + dadosGrupProd[1]);
                    System.out.println("Grupo: " + dadosGrupProd[2]);
                    System.out.println("Estoque: " + dadosGrupProd[3]);
                    System.out.println("Preco: " + dadosGrupProd[4]);

                    dadosRelProdG[g][0] = dadosGrupProd[0];
                    dadosRelProdG[g][1] = dadosGrupProd[2];
                    dadosRelProdG[g][2] = dadosGrupProd[1];
                    dadosRelProdG[g][3] = dadosGrupProd[3];
                    dadosRelProdG[g][4] = dadosGrupProd[4];
                    achouGrupo = true;

                    g++;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!achouGrupo) {
                  System.out.println(Estudos_independetes.menu("NENHUM PRODUTO ENCONTRADO"));
                }
                while(validRelProdG != 1 && validRelProdG != 2){
                validRelProdG = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
                if(validRelProdG == 1){
                  System.out.println(saveRelProdG(dadosRelProdG,contProdGroup));
                }

                if(validRelProdG == 2){
                  break;
                }

                if(validRelProdG != 1 && validRelProdG != 2){
                  System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
                }
              }
                break;

              // Finalizar a tela de consulta
              case (4):
                System.out.println(Estudos_independetes.menu("VOLTANDO"));
                break;

              default:
                System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
                break;
            }

          }

            while (pr_con != 4);

}
}
