package Labirinto;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Labirinto
{
    private char[][] labirinto ;

    public Labirinto() {}

    public void carregaLabirinto()
    {
        String filename= "meulabirinto.txt";
        Charset utf8 = StandardCharsets.UTF_8;
        int linha = 0 ;
        int coluna = 0;
        int conta = 0;

        try {
            var in = new BufferedReader(
                    new InputStreamReader( new FileInputStream(filename), utf8) );

            String line = in.readLine();
            while( line != null ) {  // null indicates the end of steam.
                if(conta == 0){ linha = Integer.parseInt(line.trim()); }
                if(conta == 1) { coluna = Integer.parseInt(line.trim());}
                conta++;
                line = in.readLine();

                if(conta > 1)
                {
                    labirinto = new char[linha][coluna];

                    for (int l = 0; l < linha; l++) {
                        if (line == null) { break;}
                        for (int c = 0; c < coluna; c++) {
                            labirinto[l][c] = line.charAt(c);
                        }

                        line = in.readLine();

                    }
                }


            }
            in.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Arquivo \""+filename+"\" nao existe.");
        } catch (IOException e)
        {
            System.out.println("Erro na leitura de " + filename+".");
        }

    }


    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();

        for ( var row : labirinto ) {
            for ( char item : row ) {
                resultado.append(item);
            }
            resultado.append("\n");
        }

        return resultado.toString();
    }
}
