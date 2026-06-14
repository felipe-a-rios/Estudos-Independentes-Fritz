public class Relatorios {
//Função relatório de pedidos
public static void relPed(Arquivo pedidos){
  int rPed, Poss1, Poss2= 0;
  String linha = "";
  int validRelPed = 0;
  int contPed = 0;

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
      //Todos os pedidos
      case (1):
                pedidos.abrirLeitura();
                linha = pedidos.lerLinha();
                contPed++;
                double valorTot = 0;

                String dadosTODOS[] = new String[4];
                String dadosRelPed[][] = new String[contPed][4];
                while (linha != null) {
                  for(int i = 0; i < contPed - 1; i++){
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

                    System.out.println(Estudos_independetes.menu("TODOS OS PEDIDOS"));
                    System.out.println("CPF: " + dadosTODOS[0]);
                    System.out.println("Cod. Produto: " + dadosTODOS[1]);
                    System.out.println("Quantidade: " + dadosTODOS[2]);
                    System.out.println("Valor Total: " + dadosTODOS[3]);
                    System.out.println("====================================================== ");
                    double valConvert = Double.parseDouble(dadosTODOS[3]);
                    valorTot = valConvert + valorTot;
                linha = pedidos.lerLinha();
                  dadosRelPed[i][0] = dadosTODOS[0];
                  dadosRelPed[i][1] = dadosTODOS[1];
                  dadosRelPed[i][2] = dadosTODOS[2];
                  dadosRelPed[i][3] = dadosTODOS[3];
                  }
                }
                System.out.println("Valor total: " + valorTot);
                System.out.println("====================================================== ");
                System.out.println("");
                System.out.println("");
                pedidos.fecharArquivo();

                while(validRelPed != 1 && validRelPed != 2){
                validRelPed = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
                if(validRelPed == 1){
                  dadosRelPed[contPed - 1][0] = Double.toString(valorTot);
                  saveRelPed(dadosRelPed,contPed);
                  System.out.println(saveRelPed(dadosRelPed, contPed));
                }

                if(validRelPed == 2){
                  break;
                }

                if(validRelPed != 1 && validRelPed != 2){
                  System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
                }
              }
      break;
      //pedidos por cliente
      case (2):
        String nomeBusca = Entrada.leiaString(Estudos_independetes.menuJanela("CONSULTA POR CLIENTE")+"Digite o CPF:");

                pedidos.abrirLeitura();
                linha = pedidos.lerLinha();
                double valorTotCli= 0;
                boolean achouCPF = false;
                String dadosCPF[] = new String[4];

                //String dadosTODOSCli[] = new String[4];
                //String dadosRelCli[][] = new String[contPed][4];
                while (linha != null) {
                  //for(int j = 0; j < contPed; j++){
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
                    System.out.println(Estudos_independetes.menu("PEDIDO POR CLIENTE"));
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
                  System.out.println(Estudos_independetes.menu("CPF NÃO ENCONTRADO"));
                  }

      break;
      case (3):
        //Pedidos por produto
        int CodProdRel = Entrada.leiaInt(Estudos_independetes.menuJanela("CONSULTA POR PRODUTO") + "Digite Código do Produto");
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
                    System.out.println(Estudos_independetes.menu("PEDIDOS POR PRODUTO"));
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
        System.out.println(Estudos_independetes.menu("VOLTANDO"));
      break;
      default:
        System.out.println(Estudos_independetes.menu("OPÇÃO INVÁLIDA"));
      break;
    }
  } while (rPed != 4);
  }
//Função de salvar o relatório
public static String saveRelPed(String[][] dadosRelPed, int contPed){
    Arquivo relatorio = new Arquivo("Relatório.txt");
    Arquivo produtos = new Arquivo("Produtos.csv");
    String linha = "";
    String CPF, CodProd,ValorTot,Quant, ValorTotPeds,CodArq;
    String NomeProd = "";
    int Poss1,Poss2 = 0;
    relatorio.abrirEscrita();
    linha = ("==========================================================================================================================\n"+
            "\n"+
             "                                     \t\t    PEDIDOS\n"+
            "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    linha = "";
    for(int i= 0; i < contPed - 1; i++){
      CPF = dadosRelPed[i][0];
      CodProd = dadosRelPed[i][1];
      Quant = dadosRelPed[i][2];
      ValorTot = dadosRelPed[i][3];
    produtos.abrirLeitura();
    linha = produtos.lerLinha();
    while(linha != null){
      Poss1 = 0;
      Poss2 = linha.indexOf(';', Poss1);
      CodArq = linha.substring(Poss1,Poss2);
      if(CodArq.equals(dadosRelPed[i][1])){
      Poss1 = Poss2 + 1;
      Poss2 = linha.indexOf(';',Poss1);
      NomeProd = linha.substring(Poss1, Poss2);
      
      break;
      }
      linha=produtos.lerLinha();
    }
    produtos.fecharArquivo();
    relatorio.abrirEscrita(true);
    linha = ("CPF:" + CPF + "\n"+
             "\n"+
             "Código do Produto: " + CodProd +"     Nome Prod.:"+ NomeProd +"         Quantidade: " + Quant + " 				Valor total: " + ValorTot + "\n"+
             "\n"+
             "==========================================================================================================================\n"+
             " ");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    }
    ValorTotPeds = dadosRelPed[contPed - 1][0];
    linha = ("TOTAL: " + ValorTotPeds);
    relatorio.abrirEscrita(true);
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    return Estudos_independetes.menu("RELATÓRIO SALVO COM SUCESSO");
    }
//Função para salvar o relatório de produtos pesquisado por nome
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
//Função para dalvar o relatório de produto por código
public static String saveRelProdC(String[][] dadosRelProdC){
    Arquivo relatorio = new Arquivo("Produtos-"+dadosRelProdC[0][0]+".txt");
    String linha = "";
    String CodProd,Nome,Grupo,Qunt,Preco;

    relatorio.abrirEscrita();
    linha = ("==========================================================================================================================\n"+
            "\n"+
             "                                     \t\tPRODUTO " + dadosRelProdC[0][0] + "\n" +
            "\n"+
             "==========================================================================================================================\n");
    relatorio.escreverLinha(linha);
    relatorio.fecharArquivo();
    linha = "";
      CodProd = dadosRelProdC[0][0];
      Nome = dadosRelProdC[0][1];
      Grupo = dadosRelProdC[0][2];
      Qunt = dadosRelProdC[0][3];
      Preco = dadosRelProdC[0][4];
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
    System.out.println(Estudos_independetes.menu("RELATÓRIO DE GRUPOS CADASTRADOS"));

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
  if(totalDeGrupos == 0){
    System.out.println(Estudos_independetes.menu("NENHUM GRUPO ENCONTRADO"));
  } else {
    System.out.println("\nGrupos Cadastrados:");
    for(int i = 0; i < totalDeGrupos; i++){
      System.out.println(" -> " + gruposUnicos[i]);
    }
    System.out.println("======================================================\n");
  }

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
                int validRelProdC = 0;

                produtos.fecharArquivo();
                String dadosRelProdC[][] = new String [1][6];
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
                    dadosRelProdC[0][0] = dadosCodProd[0];
                    dadosRelProdC[0][1] = dadosCodProd[1];
                    dadosRelProdC[0][2] = dadosCodProd[2];
                    dadosRelProdC[0][3] = dadosCodProd[3];
                    dadosRelProdC[0][4] = dadosCodProd[4];
                    
                    break;
                  }

                  linha = produtos.lerLinha();
                }

                produtos.fecharArquivo();

                if (!encontrou) {       
                  System.out.println(Estudos_independetes.menu("PRODUTO NÂO ENCONTRADO"));
                }
                while(validRelProdC != 1 && validRelProdC != 2){
                validRelProdC = Entrada.leiaInt(Estudos_independetes.menuJanela("SALVAR") + "VOCÊ QUER SALVAR ESSE RELATÓRIO ? \n            1-Sim            2-Não");
                if(validRelProdC == 1){
                  saveRelProdC(dadosRelProdC);
                  System.out.println(saveRelProdC(dadosRelProdC));
                }

                if(validRelProdC == 2){
                  break;
                }

                if(validRelProdC != 1 && validRelProdC != 2){
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
                  saveRelProdN(dadosRelProdN,contProdN);
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
                relGrupos(produtos);

                String grupoBusca = Entrada.leiaString(Estudos_independetes.menuJanela("CONSULTA POR GRUPO")+"Digite o grupo:");

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                int PossG1, PossG2 = 0;
                String dadosGrupProd[] = new String[6];
                 int contProdG = 0;
                int validRelProdG = 0;
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
                  dadosGrupProd[4] = linha.substring(PossG1,PossG2);
                  
                  PossG1 = PossG2 + 1;
                  dadosGrupProd[5] = linha.substring(PossG1);

                  if (dadosGrupProd[2].equalsIgnoreCase(grupoBusca)) {
                    contProdG++;
                  }
                  linha = produtos.lerLinha();
                }
                produtos.fecharArquivo();

                produtos.abrirLeitura();
                linha = produtos.lerLinha();
                String dadosRelProdG[][] = new String[contProdG][6];
                boolean achouGrupo = false;
                int g = 0;

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
                    System.out.println("Codigo: " + dadosGrupProd[0]);
                    System.out.println("Nome: " + dadosGrupProd[1]);
                    System.out.println("Grupo: " + dadosGrupProd[2]);
                    System.out.println("Estoque: " + dadosGrupProd[3]);
                    System.out.println("Preco: " + dadosGrupProd[4]);
                    
                    dadosRelProdG[g][0] = dadosGrupProd[0];
                    dadosRelProdG[g][1] = dadosGrupProd[1];
                    dadosRelProdG[g][2] = dadosGrupProd[2];
                    dadosRelProdG[g][3] = dadosGrupProd[3];
                    dadosRelProdG[g][4] = dadosGrupProd[4];
                    g++;
                    achouGrupo = true;
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
                  saveRelProdG(dadosRelProdG,contProdG);
                  System.out.println(saveRelProdG(dadosRelProdG, contProdG));
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
