package br.dev.phcardoso.rmi.server.model;

public class ResponseGenerated {
    private final String id;
    private final String question;
    private final String answer;
    private final String similarity;

    public ResponseGenerated(String id, String question, String answer, String similarity) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.similarity = similarity;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public double getSimilarity() {
        return Double.parseDouble(similarity);
    }
}
