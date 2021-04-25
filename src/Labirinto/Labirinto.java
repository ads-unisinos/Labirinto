package Labirinto;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Labirinto
{
    private final Charset utf8 = StandardCharsets.UTF_8;
    private char[][] labirinto ;

    public Labirinto() {}

    public char[][] carregaLabirinto(String filename){
        try {
            var in = new BufferedReader(
                    new InputStreamReader( new FileInputStream(filename), utf8) );
            int linha = 0 , coluna = 0, conta = 0;

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

        return labirinto;

    }

    public boolean labirinto ( char [][] lab) throws IllegalArgumentException
    {
        if (lab == null) { throw new IllegalArgumentException();}
        return labirinto_r (lab, 'D', 0, 0);
    }

    private boolean labirinto_r ( char [][] lab, char alvo, int l, int c)
    {
        if( l >= lab.length ){ return false;}
        if ( c < lab[l].length )
        {
            if( lab[l][c] == alvo) { return true; }
            else {

                if(lab[l][c] == 'O') { // para direita
                    lab[l][c] = 'o';
                    return labirinto_r(lab, alvo, l,c+1);
                }

                if(c - 1 > 0)
                {
                    if(lab[l][c-1] == 'O') { // para esquerda
                        lab[l][c-1] = 'o';
                        return labirinto_r(lab, alvo, l,c-1);
                    }
                }

                if (l > 0 )
                {
                    if(lab[l][c] == 'O') { // para cima
                        lab[l][c-1] = 'o';
                        return labirinto_r(lab, alvo, l+1,c);
                    }

                    if(lab[l-1][c] == 'O') { // para cima
                        lab[l][c-1] = 'o';
                        return labirinto_r(lab, alvo, l-1,c);
                    }

                }
            }
        }
        return labirinto_r(lab, alvo, l+1,0);
    }

    @Override
    public String toString()
    {
        StringBuilder resultado = new StringBuilder();

        for ( var row : labirinto )
        {
            for ( char item : row ) {
                resultado.append(item);
            }
            resultado.append("\n");
        }
        return resultado.toString();
    }

    public void labirintoOutput(String msg){
        try{
            PrintWriter out = new PrintWriter(
                        new OutputStreamWriter( new FileOutputStream("SaidaLabirinto.txt"), utf8));
            out.println(msg);
            out.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Arquivo \""+"SaidaLabirinto.txt"+"\" nao existe.");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
