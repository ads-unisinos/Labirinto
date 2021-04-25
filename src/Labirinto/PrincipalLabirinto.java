package Labirinto;

import java.util.Scanner;

public class PrincipalLabirinto {

    public static void main(String[] args)
    {
        Scanner in = new Scanner( System.in);
        Labirinto lab = new Labirinto();

        System.out.println("Informe o nome do arquivo (exemplo.txt): ");
        String filename = in.nextLine();

        String msg;

        if( lab.labirinto(lab.carregaLabirinto(filename)))
        {
            msg = "Existe um caminho para o labirinto";
            lab.labirintoOutput(msg);
        }
        else{
            msg = "Não existe um caminho para o labirinto";
            lab.labirintoOutput(msg);
        }

        System.out.println(lab);

    }

}
