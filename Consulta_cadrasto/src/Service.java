
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    Pet pet = new Pet(null,null,null,0,null,0.0,null);
    Scanner sc = new Scanner(System.in);

    List<Pet> petsCadastrados = new ArrayList<>();

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
                    Pet novoPet = pet.cadrastrarpet();
                    if (novoPet != null) {
                        petsCadastrados.add(novoPet);
                    }
                } else if (guarda == 2) {
                    alterarPet();
                } else if (guarda == 3) {
                    deletarPet();
                } else if (guarda == 4) {
                    listarTodos();
                } else if (guarda == 5) {
                    listarPorCriterio();
                }

            } catch (NumberFormatException F) {
                System.out.println("Número inválido");

            }
        }while (b == false);
        return guarda;
    }

    private void listarTodos() {
        if (petsCadastrados.isEmpty()) {
            System.out.println("Nenhum pet cadastrado ainda.");
            return;
        }
        for (int i = 0; i < petsCadastrados.size(); i++) {
            System.out.println((i + 1) + " - " + petsCadastrados.get(i));
        }
    }

    private Pet selecionarPet() {
        listarTodos();
        if (petsCadastrados.isEmpty()) {
            return null;
        }
        System.out.println("Digite o número do pet: ");
        try {
            int indice = Integer.parseInt(sc.nextLine()) - 1;
            if (indice < 0 || indice >= petsCadastrados.size()) {
                System.out.println("Número inválido.");
                return null;
            }
            return petsCadastrados.get(indice);
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            return null;
        }
    }

    private void alterarPet() {
        Pet petSelecionado = selecionarPet();
        if (petSelecionado == null) {
            return;
        }

        System.out.println("Deixe em branco para manter o valor atual.");

        System.out.println("Nome atual: " + petSelecionado.getNome());
        String nome = sc.nextLine();
        if (!nome.trim().isEmpty()) {
            petSelecionado.setNome(nome);
        }

        System.out.println("Tipo atual: " + petSelecionado.getTipo() + " (CACHORRO/GATO)");
        String tipoStr = sc.nextLine();
        if (!tipoStr.trim().isEmpty()) {
            try {
                petSelecionado.setTipo(Tipo.valueOf(tipoStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido, mantendo valor atual.");
            }
        }

        System.out.println("Sexo atual: " + petSelecionado.getSexo() + " (MACHO/FEMEA)");
        String sexoStr = sc.nextLine();
        if (!sexoStr.trim().isEmpty()) {
            try {
                petSelecionado.setSexo(Sexo.valueOf(sexoStr.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo inválido, mantendo valor atual.");
            }
        }

        System.out.println("Idade atual: " + petSelecionado.getIdade());
        String idadeStr = sc.nextLine();
        if (!idadeStr.trim().isEmpty()) {
            try {
                petSelecionado.setIdade(Integer.parseInt(idadeStr));
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida, mantendo valor atual.");
            }
        }

        System.out.println("Peso atual: " + petSelecionado.getPeso());
        String pesoStr = sc.nextLine();
        if (!pesoStr.trim().isEmpty()) {
            try {
                petSelecionado.setPeso(Double.parseDouble(pesoStr.replace(",", ".")));
            } catch (NumberFormatException e) {
                System.out.println("Peso inválido, mantendo valor atual.");
            }
        }

        System.out.println("Raça atual: " + petSelecionado.getRaca());
        String raca = sc.nextLine();
        if (!raca.trim().isEmpty()) {
            petSelecionado.setRaca(raca);
        }

        System.out.println("Endereço atual: " + petSelecionado.getEndereco());
        String endereco = sc.nextLine();
        if (!endereco.trim().isEmpty()) {
            petSelecionado.setEndereco(endereco);
        }

        System.out.println("Pet atualizado com sucesso!");
    }

    private void deletarPet() {
        Pet petSelecionado = selecionarPet();
        if (petSelecionado == null) {
            return;
        }
        petsCadastrados.remove(petSelecionado);
        System.out.println("Pet removido com sucesso!");
    }

    private void listarPorCriterio() {
        System.out.println("Filtrar por: 1-Nome  2-Idade  3-Raça");
        String opcao = sc.nextLine();
        List<Pet> resultado = new ArrayList<>();

        switch (opcao) {
            case "1":
                System.out.println("Digite o nome (ou parte dele): ");
                String nome = sc.nextLine().toLowerCase();
                for (Pet p : petsCadastrados) {
                    if (p.getNome().toLowerCase().contains(nome)) {
                        resultado.add(p);
                    }
                }
                break;
            case "2":
                System.out.println("Digite a idade: ");
                try {
                    int idade = Integer.parseInt(sc.nextLine());
                    for (Pet p : petsCadastrados) {
                        if (p.getIdade() == idade) {
                            resultado.add(p);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Idade inválida.");
                    return;
                }
                break;
            case "3":
                System.out.println("Digite a raça: ");
                String raca = sc.nextLine().toLowerCase();
                for (Pet p : petsCadastrados) {
                    if (p.getRaca().toLowerCase().contains(raca)) {
                        resultado.add(p);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        if (resultado.isEmpty()) {
            System.out.println("Nenhum pet encontrado.");
            return;
        }
        for (Pet p : resultado) {
            System.out.println(p);
        }
    }
}
