package com.evana.tabelafipe.principal;

import com.evana.tabelafipe.model.BrandDTO;
import com.evana.tabelafipe.model.CarByYearDTO;
import com.evana.tabelafipe.model.CarModelDTO;
import com.evana.tabelafipe.model.FipeDetailsDTO;
import com.evana.tabelafipe.service.BuscaAPI;
import com.evana.tabelafipe.service.ConvertJson;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final String BASE_URL = "https://fipe.parallelum.com.br/api/v2/";
    private final BuscaAPI BUSCA_API = new BuscaAPI();
    private final ConvertJson CONVERT_JSON = new ConvertJson();

    public void showMenu() {
        Scanner input = new Scanner(System.in);
        String url = null;

        System.out.println("Digite a opção desejada: ");

        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");


        var option = input.nextLine().toUpperCase();

        switch (option) {
            case "CARRO" -> url = BASE_URL + "cars/brands/";
            case "MOTO" -> url = BASE_URL + "motorcycles/brands/";
            case "CAMINHAO", "CAMINHÃO" -> url = BASE_URL + "trucks/brands/";
            default -> {
                System.out.println("Opção inválida. Digite uma das três opções.");
                option = input.nextLine().toUpperCase();
            }
        }
        var json = BUSCA_API.getData(url);

        // Converte array de marcas
        List<BrandDTO> brands = CONVERT_JSON.convertJsonToArray(json, BrandDTO[].class);

        // Imprime array em ordem crescente por código
        brands.stream()
                .sorted(Comparator.comparing(BrandDTO::code))
                .forEach(b -> System.out.print("Código: " + b.code()  + " Marca: " + b.name() + "\n"));


        // Solicita prompt do usuário para consultar modelos por código da marca
        System.out.println("Digite o código da marca desejada: ");

        int marca = input.nextInt();
        input.nextLine();

        // Busca modelos por código da marca
        json = BUSCA_API.getData(url + marca + "/models/");

        // Converte retorno em objetos modelo de carro
        List<CarModelDTO> models = CONVERT_JSON.convertJsonToArray(json, CarModelDTO[].class);

        // Exibe modelos em lista
        models.stream()
                .sorted(Comparator.comparing(CarModelDTO::name))
                .forEach(m -> System.out.println(m.code() + " - " + m.name()));

        // Solicita prompt para consultar modelo específico
//        System.out.println("Digite o modelo de carro desejado para mais detalhes: ");
//
//        int modelo = input.nextInt();
//        json = BUSCA_API.getData(url + marca + "/models/" + modelo + "/years/");
//
//        // Converte retorno em objetos anos modelo
//        List<CarByYearDTO> modelByYear = CONVERT_JSON.convertJsonToArray(json, CarByYearDTO[].class);
//
//
        System.out.println("Digite o nome do modelo desejado: ");
        var nome = input.nextLine();


        List<CarModelDTO> filterModels = models.stream()
                .filter(v -> v.name().toUpperCase().contains(nome.toUpperCase()))
                .toList();

        filterModels.stream().sorted(Comparator.comparing(CarModelDTO::code)).forEach(m ->
                System.out.println("Código: " + m.code() + " - Modelo: " + m.name()));

        System.out.println("Digite o código do modelo desejado: ");
        var cod = input.nextInt();
        input.nextLine();
        json = BUSCA_API.getData(url + marca + "/models/" + cod + "/years/");

        List<CarByYearDTO> cars = CONVERT_JSON.convertJsonToArray(json, CarByYearDTO[].class);
        cars.forEach(m -> System.out.println(m.code() + " - " + m.name()));


        System.out.println("Digite o código FIPE do modelo desejado: ");
        var codFipe = input.nextLine();
        json = BUSCA_API.getData(url + marca + "/models/" + cod + "/years/" + codFipe);

        FipeDetailsDTO filtered = CONVERT_JSON.convertJson(json, FipeDetailsDTO.class);
        System.out.println("Modelo: " + filtered.model() +
                "\nAno: " + filtered.modelYear() +
                "\nCódigo FIPE: " + filtered.codeFipe() +
                "\nPreço: " + filtered.price() +
                "\nMês de referência: " + filtered.referenceMonth());

    }
}
