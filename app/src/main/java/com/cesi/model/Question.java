package com.cesi.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> ChoiceList;
    private int answer;
    private String bonneReponse;

    public Question(String question, List<String> choiceList) {
        this.question = question;
        ChoiceList = choiceList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoiceList() {
        return ChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        ChoiceList = choiceList;
    }

    public Question(String question, List<String> choiceList, int answer, String bonneReponse) {
        this.question = question;
        ChoiceList = choiceList;
        this.answer = answer;
        this.bonneReponse = bonneReponse;
    }


    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }


    public String getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }
}
