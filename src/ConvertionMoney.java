import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConvertionMoney {
    private String apiKey = "a0144f6177344d9608e83e3a";
    Scanner sc = new Scanner(System.in);

    public Double converseMoney(String fromConversatino, String ToConversation, double amount) {

        double result = 0;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + fromConversatino))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String data = response.body();
            JSONObject jsonObject = new JSONObject(data);

            JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

            result = amount * conversionRates.getDouble(ToConversation);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;

    }

    public void printInformation() {
        System.out.println("------------------------------------");
        System.out.println("Bienvenido al conversion de moneda");
        System.out.println("------------------------------------");
        System.out.println("1) Dolar a Argentinos");
        System.out.println("2) Argentinos a Dolar");
        System.out.println("3) Brasileño a Dolar");
        System.out.println("4) Colombiano  a Dolar");
        System.out.println("5) Salir");
        System.out.println("------------------------------------");

        System.out.println("Elige la opcion valida");
        int option = sc.nextInt();

        selectOption(option);
    }

    private void selectOption(int option) {
        switch (option) {
            case 1:
                double money = enterConvertionMoney();
                money = converseMoney("USD", "ARS", money);
                printMessage("Dolares > Argentinos: " + money);
                printInformation();
                break;
            case 2:
                double money2 = enterConvertionMoney();
                money2 = converseMoney("ARS", "USD", money2);
                printMessage("Argentinos > Dolaress: " + money2);
                printInformation();
                break;

            case 3:
                double money3 = enterConvertionMoney();
                money3 = converseMoney("BRL", "USD", money3);
                printMessage("Brasileños > Dolares: " + money3);
                printInformation();
                break;
            case 4:
                double money4 = enterConvertionMoney();
                money4 = converseMoney("COP", "USD", money4);
                printMessage("Colombianos > Dolaress: " + money4);
                printInformation();
                break;
            case 5:
                System.exit(0);
                break;
        }

    }

    private Double enterConvertionMoney() {
        System.out.println("Introduzca el valor a convertir");
        return sc.nextDouble();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }


}
