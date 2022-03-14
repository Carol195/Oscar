package com.company;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Aplicacao {
    private static LeituraArquivo atores;
    private static LeituraArquivo atrizes;

    public static void main(String[] args) {

        atores = new LeituraArquivo("oscar_age_male.csv");
        atrizes = new LeituraArquivo("oscar_age_female.csv");

        atorMaisJovem();
        atrizMaisPremiada();
        atrizAge20a30();
        atrizAtoresMaisDeUmaPremiacao();
        resumoAtoresAtrizes( "Daniel Day-Lewis");

    }

    private static void atorMaisJovem() {
        List<MenuIntem> menuIntemList = atores.getMenuIntemList();
        Optional<MenuIntem> itemOptional = menuIntemList.stream()
                .min(Comparator.comparing(MenuIntem::getAge));
        itemOptional.ifPresent(it -> System.out.println("O ator mais jovem é: " + it.getName() + " com " + it.getAge() + " anos"));
    }

    private static void atrizMaisPremiada() {
        List<MenuIntem> menuIntemList = atrizes.getMenuIntemList();
        Map<String, Long> map = menuIntemList.stream()
                .map(MenuIntem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .max(Comparator.comparingLong(Entry::getValue))
                .ifPresent(c -> System.out.println("A atriz mais premiada é: " + c.getKey() + " com " + c.getValue() + " premiações"));
    }

    private static void atrizAge20a30() {
        List<MenuIntem> menuIntemList = atrizes.getMenuIntemList();
        Map<String, Long> map = menuIntemList.stream().filter(o -> o.getAge() >= 20 && o.getAge() <= 30)
                .map(MenuIntem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .max(Comparator.comparingLong(Entry::getValue))
                .ifPresent(c -> System.out.println("A atriz mais premiada com idade entre 20 a 30 anos é:" + c.getKey() + " com " + c.getValue() + " premiações"));
    }
    private static void atrizAtoresMaisDeUmaPremiacao() {
        List<MenuIntem> menuIntemList = atrizes.getMenuIntemList();
        Map<String, Long> mapAtrizes = menuIntemList.stream()
                .map(MenuIntem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        List<MenuIntem> menuIntemAtores = atores.getMenuIntemList();
        Map<String, Long> mapAtores = menuIntemAtores.stream()
                .map(MenuIntem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        mapAtores.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> {
                    System.out.println("ator " + e.getKey() + " tem " + e.getValue() + " oscars");
                });

        mapAtrizes.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .forEach(e -> {
                    System.out.println("atriz " + e.getKey() + " tem " + e.getValue() + " oscars");
                });
    }

    private static void resumoAtoresAtrizes(String name){
        List<MenuIntem> menuIntemList = atrizes.getMenuIntemList();
        List<MenuIntem> atrizes = menuIntemList.stream().filter(o->o.getName().equals(name))
                .collect(Collectors.toList());
        Long contagemAtrizes = atrizes.stream().count();
        if(contagemAtrizes!=0) {
            System.out.println("| Prêmios: " + contagemAtrizes + "| Nome: " + atrizes.get(0));
        }

        List<MenuIntem> menuIntemAtores = atores.getMenuIntemList();
        List<MenuIntem> atores = menuIntemAtores.stream().filter(o->o.getName().equals(name))
                .collect(Collectors.toList());
        Long contagemAtores = atores.stream().count();
        if(contagemAtores!=0) {
            System.out.println("| Prêmios: " + contagemAtores + "| " + atores.get(0) );
        }

        }
    }






