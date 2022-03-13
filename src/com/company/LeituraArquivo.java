package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraArquivo {

    public List<MenuIntem> menuIntemList;

    public LeituraArquivo(String filename){
        this.menuIntemList= lerArquivo(filename);
    }

    private List<MenuIntem> lerArquivo (String filename){
        try (Stream<String> fileLines = Files.lines(Paths.get(filename))) {
            return fileLines
                    .skip(1)
                    .map(MenuIntem::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<MenuIntem> getMenuIntemList(){
        return menuIntemList;
    }
}