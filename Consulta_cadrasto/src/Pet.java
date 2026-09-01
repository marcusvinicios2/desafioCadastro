
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public String getNome(){
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public String getRaca(){
        return raca;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return nome + " | " + tipo + " | " + sexo
                + " | Idade: " + idade + " | Peso: " + peso
                + " | Raça: " + raca + " | Endereço: " + endereco;
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
        List<String> respostas = new ArrayList<>();
        List<String> nomes = new ArrayList<>();



        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            while ((line = br.readLine())!= null){
                System.out.println(line);
                resposta = sc.nextLine();
                respostas.add(resposta);
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
                    sexo = Sexo.valueOf(resposta.toUpperCase());
                }

                else if (contador ==4 ) {
                    System.out.println(" Rua: ");
                    resposta =sc.nextLine();
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
                        guardaa = Double.parseDouble(resposta.replace(",", "."));
                        if (guardaa > 60 || guardaa < 0.5){
                            throw new IllegalArgumentException("Peso inválido ");
                        }
                    }
                    peso = guardaa;
                }

                else if (contador ==7) {
                    raca = resposta;
                    if (!raca.trim().isEmpty() && !raca.matches("[a-zA-ZÀ-ÿ ]+")) {
                        throw new IllegalArgumentException("Raça não pode conter números ou caracteres especiais!");
                    }
                    System.out.println();
                }
            }
        }catch (IOException | IllegalArgumentException o ){
            System.out.println("Error"+o.getMessage());
        }Gerador gr = new Gerador();
        gr.gerador(respostas);

        Pet pet = new Pet( tipo, sexo, nome,idade,endereco, peso, raca);
        return pet;
    }
}

