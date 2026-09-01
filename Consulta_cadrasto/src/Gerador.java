import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Gerador {
    public void gerador(List<String> respostas){
        String conteudo;
        String nomePet = respostas.get(0);
        String datahora =  LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-"));

        String pasta = "c:\\petsCadastrados\\";
        String path = pasta + nomePet+"_"+ "pets_" + datahora + ".txt";


        File diretorio = new File(pasta);
        if (!diretorio.exists()){
            diretorio.mkdirs();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path,true))) {
            for(String resposta : respostas) {
                bw.write(resposta + System.lineSeparator());
            }
        }catch (IOException o){
            System.out.println("ERROR " + o.getMessage());
        }

    }

    
}