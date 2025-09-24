package edu.infnet.repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersistCSV {
    public static final Path DIRPATH = Paths.get("src/main/java/edu/infnet/DATABASE");
    public static final String FILENAME = DIRPATH + "/database.csv";
    public static final Path FILEPATH = Paths.get(FILENAME);

    public static void save(String content) throws IOException {
        // Cria o diretório se não existir
        if (!Files.exists(DIRPATH)) {
            Files.createDirectories(DIRPATH);
        }

        // Salva a string no arquivo CSV (adiciona ao final se já existir)
        try (FileWriter writer = new FileWriter(FILENAME, true)) {
            writer.append(content).append(System.lineSeparator());
        }
    }

    public static List<String[]> read() throws IOException {
        // Verifica se o arquivo existe
        if (!Files.exists(FILEPATH)) {
            return new ArrayList<>(); // Retorna lista vazia se arquivo não existir
        }

        // Lê todas as linhas do arquivo
        List<String> linhas = Files.readAllLines(FILEPATH);
        List<String[]> resultado = new ArrayList<>();

        // Separa cada linha por; e adiciona ao resultado
        for (String linha : linhas) {
            if (!linha.trim().isEmpty()) { // Ignora linhas vazias
                String[] campos = linha.split(";");
                // Remove espaços em branco dos campos
                for (int i = 0; i < campos.length; i++) {
                    campos[i] = campos[i].trim();
                }
                resultado.add(campos);
            }
        }

        return resultado;
    }
}
