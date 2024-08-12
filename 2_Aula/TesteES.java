import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TesteES{

    public static void main(String[] args) throws Exception{

        InputStreamReader stream = new InputStreamReader(System.in);

        BufferedReader in = new BufferedReader(stream);

        System.out.print("\nInforme um String: ");

        String teste = in.readLine();

        System.out.println("\n\n\tA String lida foi: " + teste);

        System.out.print("\n\nInforme um inteiro: ");

        int numero = Integer.parseInt(in.readLine());

        System.out.println("\n\n\tO inteiro lido foi: " + numero);

    }
    
}