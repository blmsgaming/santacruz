import java.util.List;

public class TrivialQuiz {



    private String gameTitle;

    private String description;

    private String titleImg;

    private List<Question> questionSet;

    public TrivialQuiz(List<Question> questionSet, String gameTitle, String description, String titleImg){
        this.questionSet = questionSet;
        this.gameTitle = gameTitle;
        this.description = description;
        this.titleImg = titleImg;
    }

    public List<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(List<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }
}
