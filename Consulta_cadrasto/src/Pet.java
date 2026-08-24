import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;

public class Pet {

    Scanner sc = new Scanner(System.in);


  private String nome;
  private String endereco;
  private int idade;
  private double peso;
  private String raca;
  private Tipo tipo;
  private Sexo sexo;


    public Pet(Tipo tipo, Sexo sexo, String nome, int idade, String endereco, double peso, String raca) {
        this.nome=nome;
        this.idade=idade;
        this.peso=peso;
        this.endereco=endereco;
        this.tipo=tipo;
        this.sexo=sexo;
        this.raca=raca;
    }



    public Pet cadrastrarpet(){


        String raca= "";
        String path = "formulario.txt";
        Tipo tipo = null;
        Sexo sexo = null;
        int contador = 0;
            String nome = "";
            int idade = 0;
            String endereco = "";
            double peso = 0.0;
            String resposta = "";
            String line;
            double guardaa = 0;
            int guarda ;



            try(BufferedReader br = new BufferedReader(new FileReader(path))){
                while ((line = br.readLine())!= null){
                    System.out.println(line);
                    resposta = sc.nextLine();
                    contador++;


                    if (contador == 1){
                        if (resposta.trim().isEmpty()){
                            throw new IllegalArgumentException("Nome é Obrigatório!");


                        }nome = resposta;
                        if (!nome.matches("[a-zA-ZÀ-ÿ ]+")) {
                            throw new IllegalArgumentException("Nome não pode conter números ou caracteres especiais!");
                        }

                    }

                    else if (contador ==2) {
                         tipo = Tipo.valueOf(resposta.toUpperCase());
                    }

                    else if (contador == 3) {
                         sexo = sexo.valueOf(resposta.toUpperCase());
                    }

                    else if (contador ==4 ) {
                        System.out.println("Número De Casa: ");
                        String numero = sc.nextLine();
                        if (numero.trim().isEmpty()){
                            System.out.println("Número não informado: ");
                        }
                        endereco = resposta;
                    }

                    else if (contador ==5 ) {
                        guarda=Integer.parseInt(resposta);
                        idade = guarda;
                    }

                    else if (contador ==6 ) {
                        if (!resposta.trim().isEmpty()){
                            guardaa= Double.parseDouble(resposta.replace(",","."));
                        }if (guardaa>60 || guardaa <0.5){
                            throw new IllegalArgumentException("Peso inválido ");
                        } peso = guardaa;
                    }

                    else if (contador ==7) {
                        raca = resposta;
                        if (!raca.trim().isEmpty() && !raca.matches("[a-zA-ZÀ-ÿ ]+")) {
                            throw new IllegalArgumentException("Raça não pode conter números ou caracteres especiais!");
                        }
                    }
                }
            }catch (IOException | IllegalArgumentException o ){
                System.out.println("Error"+o.getMessage());
            }

            Pet pet = new Pet( tipo, sexo, nome,idade,endereco, peso, raca);
            return pet;
        }
    }


