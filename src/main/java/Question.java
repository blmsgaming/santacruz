import java.util.List;

public class Question {
    private String question;
    private String timeLimit;
    private String imgURL;
    private List<String> answerChoices;
    private List<Integer> correctChoices;

    public Question(String question, List<String> answerChoices,String timeLimit, List<Integer> correctChoices, String imgURL){
        this.question = question;
        this.answerChoices = answerChoices;
        this.timeLimit = timeLimit;
        this.correctChoices=correctChoices;
        this.imgURL = imgURL;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<String> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<String> answerChoices) {
        this.answerChoices = answerChoices;
    }

    public List<Integer> getCorrectChoices() {
        return correctChoices;
    }

    public void setCorrectChoices(List<Integer> correctChoices) {
        this.correctChoices = correctChoices;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
