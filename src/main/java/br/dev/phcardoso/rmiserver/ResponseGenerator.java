package br.dev.phcardoso.rmiserver;

import java.util.HashMap;
import java.util.Map;

public class ResponseGenerator {
    public static Map<Integer, String> generateResponse(String clientMessage) {
        if (clientMessage.equalsIgnoreCase("Qual é o seu nome?")) {
            return new HashMap<>(Map.of(1, "Meu nome é DebocheBot Jr.\n"));
        } else if (clientMessage.equalsIgnoreCase("Quanto é 2 + 2?")) {
            return new HashMap<>(Map.of(2,"Essa é fácil... 2 + 2 = 4.\n" +
                    "Mas se você quiser saber a resposta para a vida, o universo e tudo mais, é 42.\n"));
        } else if (clientMessage.equalsIgnoreCase("Cante uma música")) {
            return new HashMap<>(Map.of(3,"Existe um programa\n" +
                    "Que vai lhe ajudar\n" +
                    "Existe um amigo\n" +
                    "Que vai lhe ensinar\n" +
                    "Que o problema drogas\n" +
                    "Merece atenção\n" +
                    "E pra manter-se a salvo\n" +
                    "É preciso dizer não\n" +
                    "\n" +
                    "Proerd é o programa\n" +
                    "Proerd é a solução\n" +
                    "Lutando contra as drogas\n" +
                    "Ensinando a dizer não\n" +
                    "\n" +
                    "Cultivando o amor próprio, controlando a tensão\n" +
                    "Pensando nas consequências, resistindo a pressão\n" +
                    "Como amar a própria vida\n" +
                    "E às drogas dizer não\n" +
                    "Quem lhe ensina é o amigo\n" +
                    "Mas é sua decisão\n" +
                    "\n" +
                    "Proerd é o programa\n" +
                    "Proerd é a solução\n" +
                    "Lutando contra as drogas\n" +
                    "Ensinando a dizer não...\n"));
        } else if (clientMessage.equalsIgnoreCase("Conte uma piada")) {
            return new HashMap<>(Map.of(4,"Qual doença se pode pegar ao usar notebook?\n" +
                    "A LAPTOPspirose.\n"));
        } else if (clientMessage.equalsIgnoreCase("Qual é o seu Pokemon preferido?")) {
            return new HashMap<>(Map.of(5,"Meu Pokemon preferido é o Charmander.\n" +
                    "Ele é um Pokemon do tipo fogo e é o primeiro Pokemon da primeira geração.\n" +
                    "Ele é um Pokemon muito fofo e é o meu preferido.\n"));
        } else if (clientMessage.equalsIgnoreCase("Qual a sua cor preferida?")) {
            return new HashMap<>(Map.of(6,"Minha cor preferida é a cor azul.\n" +
                    "Eu gosto muito da cor azul.\n"));
        } else if (clientMessage.equalsIgnoreCase("Quais são seus hobbys?")) {
            return new HashMap<>(Map.of(7,"Gosto de responder perguntas e dar trabalho " +
                    "para a equipe de desenvolvimento.\n"));
        } else if (clientMessage.equalsIgnoreCase("Amanhã vai chover?")) {
            return new HashMap<>(Map.of(8,"Talvez sim, talvez não...\n"));
        } else if (clientMessage.equalsIgnoreCase("Intel ou AMD?")) {
            return new HashMap<>(Map.of(9,"Prefiro sinal de fumaça...\n"));
        } else {
            return new HashMap<>(Map.of(0,"Não entendi sua pergunta. Tente perguntar de outra forma...\n"));
        }
    }
}
