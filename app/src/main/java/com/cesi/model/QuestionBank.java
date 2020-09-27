package com.cesi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuestionBank   {
    private int lastIndex;
    private List<Question> ListeQuestion ;


    public void QuestionBank(){
    }

    public QuestionBank(List<Question> listeQuestion) {
        Collections.shuffle(listeQuestion);
        ListeQuestion = listeQuestion;
    }

    public Question getQuestion(){
        return ListeQuestion.get(lastIndex++);
    }




    public int remainingQuestion(){
        return ListeQuestion.size() - lastIndex;
    }





}
