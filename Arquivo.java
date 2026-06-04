import java.io.*;

/************
 * Arquivo - versao 25/05/2007
 * 
 * abrirEscrita() -- boolean
 * abrirLeitura() -- boolean
 * lerLinha() -- string
 * escreverLinha( string )
 * fecharArquivo()
 */
public class Arquivo {
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomeArquivo;
    private char status;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        status = 'C'; // Closed, Read, Write
    }

    public boolean abrirLeitura() {
        boolean sucesso = true;
        FileReader fileReader = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" ja esta aberto");
            sucesso = false;
        } else {
            try {
                fileReader = new FileReader(nomeArquivo);
            } catch (FileNotFoundException e) {
                System.err.println("-- Arquivo \"" + nomeArquivo + "\" nao encontrado");
                sucesso = false;
                // e.printStackTrace();
            }
            if (sucesso) {
                status = 'R';
                bufferedReader = new BufferedReader(fileReader);
            }
        }
        return (sucesso);
    }

    public boolean abrirEscrita() {
        boolean sucesso = true;
        FileWriter fileWriter = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" ja esta aberto");
            sucesso = false;
        } else {
            try {
                fileWriter = new FileWriter(nomeArquivo);
            } catch (IOException e) {
                System.err.println("-- Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                sucesso = false;
                // e.printStackTrace();
            }
            if (sucesso) {
                status = 'W';
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        }
        return (sucesso);
    }

    public boolean abrirEscrita(boolean append) {
        boolean sucesso = true;
        FileWriter fileWriter = null;
        File fArquivo = null;
        if (status != 'C') {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" ja esta aberto");
            sucesso = false;
        } else {
            try {
                fArquivo = new File(nomeArquivo);

                if (fArquivo.exists() == true && append == true) {
                    fileWriter = new FileWriter(nomeArquivo, true);
                } else {
                    fileWriter = new FileWriter(nomeArquivo);
                }
                // fileWriter = new FileWriter(nomeArquivo);
            } catch (IOException e) {
                System.err.println("-- Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                sucesso = false;
                // e.printStackTrace();
            }
            if (sucesso) {
                status = 'W';
                bufferedWriter = new BufferedWriter(fileWriter);
            }
        }
        return (sucesso);
    }

    public String lerLinha() {
        String linha = null;
        if (status == 'R') {
            try {
                linha = bufferedReader.readLine();
            } catch (IOException e) {
                System.err.println("-- Erro de leitura no arquivo \"" + nomeArquivo + "\"");
                // e.printStackTrace();
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" nao esta pronto para leitura");
        }
        return (linha);
    }

    public void escreverLinha(String linha) {
        if (status == 'W') {
            try {
                bufferedWriter.write(linha + "\n");
            } catch (IOException e) {
                System.err.println("Erro de escrita no arquivo \"" + nomeArquivo + "\"");
                // e.printStackTrace();
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" nao esta pronto para escrita");
        }
    }

    public void fecharArquivo() {
        if (status == 'R') // estava aberto para leitura
        {
            try {
                bufferedReader.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                // e.printStackTrace();
            }
        }
        if (status == 'W') // estava aberto para grava??o
        {
            try {
                bufferedWriter.close();
                status = 'C';
            } catch (IOException e) {
                System.err.println("Erro ao fechar o arquivo \"" + nomeArquivo + "\"");
                // e.printStackTrace();
            }
        }
    }

    public boolean excluirArquivo() {
        if (status != 'C') {
            System.err.println(
                    "-- Nao eh possivel excluir o arquivo \"" + nomeArquivo + "\" enquanto ele estiver aberto.");
            return false;
        }

        File f = new File(nomeArquivo);
        if (f.exists()) {
            boolean deletado = f.delete();
            if (deletado) {
                return true;
            } else {
                System.err.println(
                        "-- Erro ao tentar excluir o arquivo \"" + nomeArquivo + "\". Verifique as permissoes.");
                return false;
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" nao existe para ser excluido.");
            return false;
        }
    }

    /**
     * Renomeia o arquivo atual para um novo nome informado.
     * O arquivo precisa estar fechado (status 'C') para ser renomeado.
     */
    public boolean renomearArquivo(String novoNome) {
        if (status != 'C') {
            System.err.println(
                    "-- Nao eh possivel renomear o arquivo \"" + nomeArquivo + "\" enquanto ele estiver aberto.");
            return false;
        }

        File arquivoAtual = new File(nomeArquivo);
        File arquivoNovo = new File(novoNome);

        if (arquivoAtual.exists()) {
            boolean sucesso = arquivoAtual.renameTo(arquivoNovo);
            if (sucesso) {
                // Atualiza a variável interna para que o objeto Arquivo passe a apontar para o
                // novo nome
                this.nomeArquivo = novoNome;
                return true;
            } else {
                System.err.println("-- Erro ao renomear o arquivo \"" + nomeArquivo + "\" para \"" + novoNome + "\".");
                return false;
            }
        } else {
            System.err.println("-- Arquivo \"" + nomeArquivo + "\" nao existe para ser renomeado.");
            return false;
        }
    }
}