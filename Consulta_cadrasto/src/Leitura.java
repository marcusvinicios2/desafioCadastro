void main(String[] args) {
    Service service = new Service();
    Scanner sc = new Scanner(System.in);
    String path = "formulario.txt";
    int opcao = service.capturaropcao();
    System.out.println("Você escolheu a opção: " + opcao);
    }