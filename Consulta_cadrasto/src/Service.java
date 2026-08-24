import java.util.Scanner;

public class Service {
    Pet pet = new Pet(null,null,null,0,null,0.0,null);
    Scanner sc = new Scanner(System.in);

    void exibirmenu() {
        System.out.println("1.Cadastrar um novo pet");
        System.out.println("2.Alterar os dados do pet cadastrado");
        System.out.println("3.Deletar um pet cadastrado");
        System.out.println("4.Listar todos os pets cadastrados");
        System.out.println("5.Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6.Sair");
    }

     public int capturaropcao() {

        String a;
        int guarda =0;
        boolean b = false;
        do {
            exibirmenu();
            a = sc.nextLine();
            try {
                guarda=Integer.parseInt(a);
                if (guarda<6 && guarda>0){
                    b = true;
                } else if (guarda ==6) {
                    System.exit(0);

                }
                if (guarda == 1){
                    pet.cadrastrarpet();
                }

            } catch (NumberFormatException F) {
                System.out.println("Número inválido");

            }
        }while (b == false);
         return guarda;
     }
}
